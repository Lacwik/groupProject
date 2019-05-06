package com.wfiis.CalculatorCO2.module.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.ResourceFlagsAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleAssembler {

    private final ResourceFlagsAssembler resourceFlagsAssembler;

    public List<ModuleCreateModel> getCreateModelsFromEntityList(List<Module> modules) {
        List<ModuleCreateModel> outStages = new ArrayList<>();
        for (Module module : modules) {
            outStages.add(getCreateModelFromEntity(module));
        }
        return outStages;
    }

    public ModuleCreateModel getCreateModelFromEntity(Module module) {
        return new ModuleCreateModel(
                module.getName(),
                module.getPower(),
                resourceFlagsAssembler.getCreateModelFromEntity(module.getResourceFlags()),
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
                module.getPower(),
                resourceFlagsAssembler.getCreateModelFromEntity(module.getResourceFlags()),
                module.getVegetables(),
                module.getId()
        );
    }

    public Module getNewEntityFromModel(ModuleCreateModel moduleCreateModel, Company company){
        return new Module(
                null,
                moduleCreateModel.getName(),
                false,
                moduleCreateModel.getPower(),
                resourceFlagsAssembler.getNewEntityFromModel(moduleCreateModel.getResourceFlagsCreateModel()),
                company,
                moduleCreateModel.getVegetables(),
                new ArrayList<>(),
                true
        );
    }
}
