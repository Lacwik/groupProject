package com.wfiis.CalculatorCO2.vegetable.metadata;

import com.wfiis.CalculatorCO2.vegetable.exceptions.VegetableNotFoundException;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import com.wfiis.CalculatorCO2.vegetable.metadata.repository.VegetableRepository;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VegetableAssembler {
    private final VegetableRepository vegetableRepository;

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

    public Vegetable getEntity(Long vegetableId) {
        return vegetableRepository.findById(vegetableId).orElseThrow(() -> new VegetableNotFoundException(vegetableId));
    }

    public List<Vegetable> getEntityFromModelList(List<VegetableModel> vegetableModels){
        List<Vegetable> outVegetables = new ArrayList<>();
        for (VegetableModel vegetableModel : vegetableModels) {
            outVegetables.add(getEntity(vegetableModel.getId()));
        }
        return outVegetables;
    }
}
