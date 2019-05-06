package com.wfiis.CalculatorCO2.module;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleFacade {
    private final ModuleService moduleService;
    private final UserMetadataService userMetadataService;

    public ModuleModel createModule(ModuleCreateModel moduleCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.addModule(CompanyIdentity.of(company.getId()), moduleCreateModel, company);
    }

    public ModuleModel editModule(ModuleCreateModel moduleCreateModel, Long moduleId){
        return moduleService.editModule(
                CompanyIdentity.of(
                        moduleService.getCompanyByModuleId(moduleId).getId()
                ),
                moduleCreateModel,
                moduleId
        );
    }

    public List<ModuleModel> getCompanyModules(Long userId){
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getCompanyModules(CompanyIdentity.of(company.getId()), company);
    }

    public ModuleModel getModule(Long userId, Long moduleId){
        return moduleService.getModuleModelById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                moduleId
        );
    }

    public List<StageModel> getModuleStagesById(Long userId, Long moduleId){
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getModuleStagesById(CompanyIdentity.of(company.getId()), moduleId);
    }

    public void deleteModule(Long userId, Long moduleId){
        moduleService.deleteModuleById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                moduleId
        );
    }
}
