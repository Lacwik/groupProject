package com.wfiis.CalculatorCO2.module.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.module.exceptions.ModuleNotFoundException;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.metadata.repository.ModuleRepository;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleAssembler moduleAssembler;
    private final StageAssembler stageAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ModuleModel> getCompanyModules(CompanyIdentity companyIdentity, Company company) {
        return moduleAssembler.getModelsFromEntityList(moduleRepository.findModulesByCompany(company));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public ModuleModel addModule(CompanyIdentity companyIdentity, ModuleCreateModel moduleCreateModel, Company company){
        Module module = moduleRepository.save(moduleAssembler.getNewEntityFromModel(moduleCreateModel, company));
        return moduleAssembler.getModelFromEntity(module);
    }

    public Company getCompanyByModuleId(Long moduleId){
        return getModuleById(moduleId).getCompany();
    }

    public Module getModuleById(Long moduleId){
        return moduleRepository.findById(moduleId).orElseThrow(()->new ModuleNotFoundException(moduleId));
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public ModuleModel editModule(CompanyIdentity companyIdentity, ModuleCreateModel moduleCreateModel, Long moduleId){
        Module module = getModuleById(moduleId);
        module.setName(moduleCreateModel.getName());
        module.setLoss(moduleCreateModel.getLoss());
        module.setWaste(moduleCreateModel.getWaste());
        module.setTime(moduleCreateModel.getTime());
        module.setResource(moduleCreateModel.getResource());
        module.setVegetables(moduleCreateModel.getVegetables());
        return moduleAssembler.getModelFromEntity(module);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public ModuleModel getModuleModelById(CompanyIdentity companyIdentity, Long moduleId){
        return moduleAssembler.getModelFromEntity(getModuleById(moduleId));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public List<StageModel> getModuleStagesById(CompanyIdentity companyIdentity, Long moduleId){
        return stageAssembler.getModelsFromEntityList(getModuleById(moduleId).getStages());
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public void deleteModuleById(CompanyIdentity companyIdentity, Long moduleId){
        moduleRepository.delete(getModuleById(moduleId));
    }
}
