package com.wfiis.CalculatorCO2.leftover.metadata;

import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LeftoverAssembler {
    public LeftoverModel getModelFromEntity(Leftover leftover) {
        return new LeftoverModel(
                leftover.getName(),
                leftover.getId()
        );
    }

    public List<LeftoverModel> getModelsFromEntityList(List<Leftover> leftovers) {
        List<LeftoverModel> outLeftovers = new ArrayList<>();
        for (Leftover leftover : leftovers) {
            outLeftovers.add(getModelFromEntity(leftover));
        }
        return outLeftovers;
    }
}
