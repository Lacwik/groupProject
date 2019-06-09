package com.wfiis.CalculatorCO2.calculator;

import com.wfiis.CalculatorCO2.calculator.models.CalculatorStage;
import com.wfiis.CalculatorCO2.calculator.models.CalendarFormModel;
import com.wfiis.CalculatorCO2.calculator.models.EmittedCo2;
import com.wfiis.CalculatorCO2.calculator.models.GusResource;
import com.wfiis.CalculatorCO2.calculator.models.ObjectValueWithUnit;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.leftover.metadata.LeftoverService;
import com.wfiis.CalculatorCO2.line.metadata.LineService;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.resource.metadata.ResourceService;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.statistics.metadata.LineStatisticsService;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.LineStatistics;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.StatisticsStage;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.StatisticsStageLeftover;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.StatisticsStageModule;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.StatisticsStageResource;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class CalculatorFacade {
    private final StageService stageService;
    private final ResourceService resourceService;
    private final LeftoverService leftoverService;
    private final VegetableService vegetableService;
    private final LineService lineService;
    private final UserMetadataService userMetadataService;
    private final LineStatisticsService lineStatisticsService;

    public EmittedCo2 calculate(UsernamePasswordAuthenticationToken authModel, CalendarFormModel calendarFormModel) {
        EmittedCo2 co2FromResources = calculateResources(calendarFormModel.getResources(), calendarFormModel.getStages());

        saveToStatistics(calendarFormModel, authModel, co2FromResources);
        return co2FromResources;
    }

    private LineStatistics saveToStatistics(CalendarFormModel calendarFormModel, UsernamePasswordAuthenticationToken authModel, EmittedCo2 co2FromResources) {
        List<StatisticsStage> statisticsStages = new LinkedList<>();
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) authModel.getPrincipal();

        for (CalculatorStage calculatorStage : calendarFormModel.getStages()) {
            Stage stage = stageService.getStageEntity(calculatorStage.getId());
            List<StatisticsStageResource> stageResources = new LinkedList<>();
            List<StatisticsStageLeftover> stageLeftovers = new LinkedList<>();

            for (Map.Entry<Long, ObjectValueWithUnit> resourceEntry : calculatorStage.getResources().entrySet()) {
                Optional<GusResource> gusResource = calendarFormModel.getResources().stream()
                        .filter(gr -> !gr.getId().equals(resourceEntry.getKey()))
                        .findFirst();

                Resource resource = null;
                if (gusResource.isPresent()) {
                    String gus = gusResource.get().getGus_id();
                    resource = resourceService.getResourseByGusId(gus);
                }

                stageResources.add(StatisticsStageResource.builder()
                        .resource(resource)
                        .value(resourceEntry.getValue().getValue())
                        .unitId(resourceEntry.getValue().getUnitId())
                        .gusName(gusResource.get().getName_pl())
                        .build());
            }

            for (Map.Entry<Long, ObjectValueWithUnit> leftoverEntry : calculatorStage.getLeftovers().entrySet()) {
                Long leftoverId = leftoverEntry.getKey();


                StatisticsStageLeftover stageLeftover = StatisticsStageLeftover.builder()
                        .value(leftoverEntry.getValue().getValue())
                        .leftover(leftoverService.getLeftoverEntity(leftoverId))
                        .build();

                stageLeftovers.add(stageLeftover);
            }

            List<StatisticsStageModule> statisticsStageModules = new LinkedList<>();

            for (Module module : stage.getModules()) {
                StatisticsStageModule statisticsStageModule = StatisticsStageModule.builder()
                        .name(module.getName())
                        .power(module.getPower())
                        .build();

                statisticsStageModules.add(statisticsStageModule);
            }

            StatisticsStage statisticsStage = StatisticsStage.builder()
                    .stage(stage)
                    .stageLeftovers(stageLeftovers)
                    .stageResources(stageResources)
                    .statisticsStageModules(statisticsStageModules)
                    .time(calculatorStage.getDuration().getSeconds())
                    .carbonPrint(co2FromResources.getCo2PerStage().get(calculatorStage.getId()))
                    .build();

            statisticsStages.add(statisticsStage);
        }

        Company company = userMetadataService.getCurrentCompanyWorkingFor(principal.getId());

        LineStatistics lineStatistics = LineStatistics.builder()
                .company(company)
                .line(lineService.getLineEntity(calendarFormModel.getLineId()))
                .carbonPrint(co2FromResources.getCo2PerLine())
                .vegetable(vegetableService.getVegetableEntity(calendarFormModel.getVegetableId()))
                .statisticsStages(statisticsStages)
                .productWeight(calendarFormModel.getProduct().getValue())
                .materialWeight(calendarFormModel.getMaterial().getValue())
                .build();


        return lineStatisticsService.createStatistic(lineStatistics);
    }

    private EmittedCo2 calculateResources(List<GusResource> gusResources, List<CalculatorStage> calculatorStages) {
        Float co2 = 0.0f;
        Map<Long, Float> co2PerStage = new HashMap<>();

        for (GusResource resource : gusResources) {
            final Long resourceId = resource.getId();
            final Float co2PerUnit = resource.getEquiv_kgCo2();

            for (CalculatorStage stage : calculatorStages) {
                Optional<ObjectValueWithUnit> value = Optional.ofNullable(stage.getResources().get(resourceId));

                if (value.isPresent()) {
                    Float co2FromResourceByStage = value.get().getValue() * co2PerUnit;
                    co2 += co2FromResourceByStage;
                    co2PerStage.merge(stage.getId(), co2FromResourceByStage, Float::sum);
                }
            }
        }

        return new EmittedCo2(co2PerStage, co2);
    }
}
