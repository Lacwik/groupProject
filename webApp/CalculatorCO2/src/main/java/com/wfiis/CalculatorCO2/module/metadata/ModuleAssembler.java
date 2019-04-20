package com.wfiis.CalculatorCO2.module.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleAssembler {

    public List<ModuleCreateModel> getCreateModelsFromEntityList(List<Module> moduels) {
        List<ModuleCreateModel> outStages = new ArrayList<>();
        for (Module module : moduels) {
            outStages.add(getCreateModelFromEntity(module));
        }
        return outStages;
    }

    public ModuleCreateModel getCreateModelFromEntity(Module module) {
        return new ModuleCreateModel(
                module.getName(),
                module.getLoss(),
                module.getWaste(),
                module.getTime(),
                module.getResource(),
                module.getVegetables()
        );
    }

    public List<ModuleModel> getModelsFromEntityList(List<Module> modules) {
        List<ModuleModel> outStages = new ArrayList<>();
        for (Module module : modules) {
            outStages.add(getModelFromEntity(module));
        }
        return outStages;
    }

    public ModuleModel getModelFromEntity(Module module) {
        return new ModuleModel(
                module.getName(),
                module.getLoss(),
                module.getWaste(),
                module.getTime(),
                module.getResource(),
                module.getVegetables(),
                module.getId()
        );
    }

    public Module getNewEntityFromModel(ModuleCreateModel moduleCreateModel, Company company){
        return new Module(
                null,
                moduleCreateModel.getName(),
                moduleCreateModel.getLoss(),
                moduleCreateModel.getWaste(),
                moduleCreateModel.getTime(),
                false,
                moduleCreateModel.getResource(),
                company,
                moduleCreateModel.getVegetables(),
                new ArrayList<>()
        );
    }
}
