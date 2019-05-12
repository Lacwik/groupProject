package com.wfiis.CalculatorCO2.vegetable.metadata;

import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VegetableAssembler {
    public VegetableModel getModelFromEntity(Vegetable vegetable) {
        return new VegetableModel(
                vegetable.getName(),
                vegetable.getId()
        );
    }

    public List<VegetableModel> getModelsFromEntityList(List<Vegetable> vegetables) {
        List<VegetableModel> outVegetables = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            outVegetables.add(getModelFromEntity(vegetable));
        }
        return outVegetables;
    }
}
