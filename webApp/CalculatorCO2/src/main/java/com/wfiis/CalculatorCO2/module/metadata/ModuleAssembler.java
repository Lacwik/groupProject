package com.wfiis.CalculatorCO2.module.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.exceptions.ModuleNotFoundException;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.metadata.repository.ModuleRepository;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleAssembler {
    private final ModuleRepository moduleRepository;

    public Module getEntity(Long moduleId) {
        return moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException(moduleId));
    }

    public List<Module> getEntityFromModelList(List<ModuleModel> moduleModels){
        List<Module> modules = new ArrayList<>();
        for (ModuleModel moduleModel : moduleModels){
            modules.add(getEntity(moduleModel.getId()));
        }
        return modules;
    }

    public List<ModuleCreateModel> getCreateModelsFromEntityList(List<Module> modules) {
        List<ModuleCreateModel> outModules = new ArrayList<>();
        for (Module module : modules) {
            outModules.add(getCreateModelFromEntity(module));
        }
        return outModules;
    }

    public ModuleCreateModel getCreateModelFromEntity(Module module) {
        return new ModuleCreateModel(
                module.getName(),
                module.getPower(),
                module.getVegetables(),
                module.getResources(),
                module.getLeftovers()
        );
    }

    public List<ModuleModel> getModelsFromEntityList(List<Module> modules) {
        List<ModuleModel> outModules = new ArrayList<>();
        for (Module module : modules) {
            outModules.add(getModelFromEntity(module));
        }
        return outModules;
    }

    public ModuleModel getModelFromEntity(Module module) {
        return new ModuleModel(
                module.getName(),
                module.getPower(),
                module.getVegetables(),
                module.getResources(),
                module.getLeftovers(),
                module.getId()
        );
    }

    public Module getNewEntityFromModel(ModuleCreateModel moduleCreateModel, Company company){
        return new Module(
                null,
                moduleCreateModel.getName(),
                moduleCreateModel.getPower(),
                false,
                false,
                company,
                new ArrayList<>(),
                moduleCreateModel.getVegetables(),
                moduleCreateModel.getResources(),
                moduleCreateModel.getLeftovers()
        );
    }
}
