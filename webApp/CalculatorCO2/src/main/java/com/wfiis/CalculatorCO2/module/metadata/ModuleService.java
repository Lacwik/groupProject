package com.wfiis.CalculatorCO2.module.metadata;

import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.metadata.repository.ModuleRepository;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;

import java.util.ArrayList;
import java.util.List;

public class ModuleService {
    private ModuleRepository moduleRepository;
    private VegetableService vegetableService;

    public List<Module> getOutsourcedModules(){
        return moduleRepository.findModulesByOutsourced(1);
    }

    public List<Module> getCompanyModules(Long companyId){
        return moduleRepository.findModulesByCompanyId(companyId);
    }

    public List<ModuleModel> getModelsFromEntityList(List<Module> modules) {
        List<ModuleModel> outModules = new ArrayList<>();
        for (Module module : modules) {
            outModules.add(getModelFromEntity(module));
        }
        return outModules;
    }

    public ModuleModel getModelFromEntity(Module module) {
        return new ModuleModel(module.getId(),
                module.getName(),
                module.getLoss(),
                module.getWaste(),
                module.getPower(),
                module.getTime(),
                module.getOutsourced(),
                module.getResource(),
                vegetableService.getModelsFromEntityList(module.getVegetables())
        );
    }
}
