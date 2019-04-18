package com.wfiis.CalculatorCO2.module;

import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleFacade {
    private ModuleService moduleService;

    public List<ModuleModel> getOutsourcedModules(){
        return moduleService.getModelsFromEntityList(
                moduleService.getOutsourcedModules()
        );
    }

    public List<ModuleModel> getCompanyModules(Long companyId){
        return moduleService.getModelsFromEntityList(
                moduleService.getCompanyModules(companyId)
        );
    }

}
