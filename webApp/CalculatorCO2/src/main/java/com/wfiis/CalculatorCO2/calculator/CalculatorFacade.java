package com.wfiis.CalculatorCO2.calculator;

import com.wfiis.CalculatorCO2.calculator.models.CalculatorStage;
import com.wfiis.CalculatorCO2.calculator.models.CalendarFormModel;
import com.wfiis.CalculatorCO2.calculator.models.GusResource;
import com.wfiis.CalculatorCO2.calculator.models.ObjectValueWithUnit;
import com.wfiis.CalculatorCO2.module.ModuleFacade;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.stage.StageFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class CalculatorFacade {
    public Float calculate(UsernamePasswordAuthenticationToken authModel, CalendarFormModel calendarFormModel) {
        Float co2FromResources = calculateResources(calendarFormModel.getResources(), calendarFormModel.getStages());


        return co2FromResources;
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
                            co2 += value.get().getValue() * co2PerKg;
                            break;
                        case "tona":
                            co2 += value.get().getValue() * 1000 * co2PerKg;
                            break;
                        default:
                            log.error("UNKNOWN UNIT: {} WHILE CALCULATING CO2 FOR RESOURCE: {} IN STAGE: {}", value.get().getUnit(), resource, stage);
                    }
                }
            }
        }

        return co2;
    }
}
