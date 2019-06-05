package com.wfiis.CalculatorCO2.calculator;

import com.wfiis.CalculatorCO2.calculator.models.CalculatorStage;
import com.wfiis.CalculatorCO2.calculator.models.CalendarFormModel;
import com.wfiis.CalculatorCO2.calculator.models.GusResource;
import com.wfiis.CalculatorCO2.calculator.models.ObjectValueWithUnit;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.LineService;
import com.wfiis.CalculatorCO2.lineStatistics.LineStatisticsFacade;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.resource.metadata.ResourceService;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stageResourceValue.model.StageResourceValueCreateModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class CalculatorFacade {
    private final LineStatisticsFacade lineStatisticsFacade;
    private final StageService stageService;
    private final ResourceService resourceService;
    private final VegetableService vegetableService;
    private final LineService lineService;
    private final UserMetadataService userMetadataService;

    public Float calculate(UsernamePasswordAuthenticationToken authModel, CalendarFormModel calendarFormModel) {
        Float co2FromResources = calculateResources(calendarFormModel.getResources(), calendarFormModel.getStages());

        saveToStatistics(calendarFormModel, authModel, co2FromResources);
        return co2FromResources;
    }

    private LineStatisticsCreateModel saveToStatistics(CalendarFormModel calendarFormModel, UsernamePasswordAuthenticationToken authModel, Float co2FromResources) {
        List<StageResourceValueCreateModel> createModels = new LinkedList<>();

        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) authModel.getPrincipal();

        for (CalculatorStage calculatorStage : calendarFormModel.getStages()) {
            Stage stage = stageService.getStageEntity(calculatorStage.getId());
            for (Map.Entry<Long, ObjectValueWithUnit> resourceEntry : calculatorStage.getResources().entrySet()) {
                Optional<GusResource> gusResource = calendarFormModel.getResources().stream()
                        .filter(gr -> !gr.getId().equals(resourceEntry.getKey()))
                        .findFirst();

                Resource resource = null;
                if (gusResource.isPresent()) {
                    String gus = gusResource.get().getGus_id();
                    resource = resourceService.getResourseByGusId(gus);
                }

                StageResourceValueCreateModel resourceValue = StageResourceValueCreateModel.builder()
                        .value(resourceEntry.getValue().getValue())
                        .stage(stage)
                        .resource(resource)
                        .time(calculatorStage.getDuration().getSeconds())
                        .build();

                createModels.add(resourceValue);
            }
        }

        Company company = userMetadataService.getCurrentCompanyWorkingFor(principal.getId());

        LineStatisticsCreateModel lineStatistics = LineStatisticsCreateModel.builder()
                .stageResourceValueCM(createModels)
                .vegetable(vegetableService.getVegetableEntity(calendarFormModel.getVegetableId()))
                .line(lineService.getLineEntity(calendarFormModel.getLineId()))
                .carbonPrint(co2FromResources)
                .company(company)
                .build();


        return lineStatisticsFacade.createLineStatistics(lineStatistics, principal.getId());
    }

    private Float calculateResources(List<GusResource> gusResources, List<CalculatorStage> calculatorStages) {
        Float co2 = 0.0f;

        for (GusResource resource : gusResources) {
            final Long resourceId = resource.getId();
            final Float co2PerKg = resource.getEquiv_kgCo2();

            for (CalculatorStage stage : calculatorStages) {
                Optional<ObjectValueWithUnit> value = Optional.ofNullable(stage.getResources().get(resourceId));

                if (value.isPresent()) {
                    switch (value.get().getUnit()) {
                        case "kg":
                        case "kWh":
                        case "GJ":
                            co2 += value.get().getValue() * co2PerKg;
                            break;
                        case "tona":
                            co2 += value.get().getValue() * 1000 * co2PerKg;
                            break;
                        case "J":
                            co2 += value.get().getValue() / 1000000000 * co2PerKg;
                        default:
                            log.error("UNKNOWN UNIT: {} WHILE CALCULATING CO2 FOR RESOURCE: {} IN STAGE: {}", value.get().getUnit(), resource, stage);
                    }
                }
            }
        }

        return co2;
    }
}
