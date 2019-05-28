package com.wfiis.CalculatorCO2.module;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
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
        return moduleService.createModule(CompanyIdentity.of(company.getId()), moduleCreateModel, company);
    }

    public ModuleModel editModule(ModuleCreateModel moduleCreateModel, Long moduleId) {
        Company company = moduleService.getCompanyByModuleId(moduleId);
        return moduleService.editModule(CompanyIdentity.of(company.getId()), moduleCreateModel, moduleId);
    }

    public ModuleModel getModule(Long userId, Long moduleId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getModule(CompanyIdentity.of(company.getId()), moduleId);
    }

    public String deleteModule(Long userId, Long moduleId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.deleteModule(CompanyIdentity.of(company.getId()), moduleId);
    }

    public List<ModuleModel> getCompanyModules(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getCompanyModules(CompanyIdentity.of(company.getId()), company);
    }

    public List<StageModel> getModuleStages(Long userId, Long moduleId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getModuleStages(CompanyIdentity.of(company.getId()), moduleId);
    }

    public List<VegetableModel> getModuleVegetables(Long userId, Long moduleId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getModuleVegetables(CompanyIdentity.of(company.getId()), moduleId);
    }

    public List<ResourceModel> getModuleResources(Long userId, Long moduleId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getModuleResources(CompanyIdentity.of(company.getId()), moduleId);
    }

    public List<LeftoverModel> getModuleLeftovers(Long userId, Long moduleId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getModuleLeftovers(CompanyIdentity.of(company.getId()), moduleId);
    }

    public List<ModuleModel> getDefaultModules(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return moduleService.getDefaultModules(CompanyIdentity.of(company.getId()), company);
    }
}
