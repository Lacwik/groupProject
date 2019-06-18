package com.wfiis.CalculatorCO2.module.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.metadata.LeftoverAssembler;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.module.exceptions.ModuleNotFoundException;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.metadata.repository.ModuleRepository;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.metadata.ResourceAssembler;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableAssembler;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ModuleAssembler moduleAssembler;
    private final StageAssembler stageAssembler;
    private final VegetableAssembler vegetableAssembler;
    private final ResourceAssembler resourceAssembler;
    private final LeftoverAssembler leftoverAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public ModuleModel createModule(CompanyIdentity companyIdentity, ModuleCreateModel moduleCreateModel, Company company) {
        Module module = moduleRepository.save(moduleAssembler.getNewEntityFromModel(moduleCreateModel, company));
        return moduleAssembler.getModelFromEntity(module);
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public ModuleModel editModule(CompanyIdentity companyIdentity, ModuleCreateModel moduleCreateModel, Long moduleId) {
        Module module = getModuleEntity(moduleId);

        if (module.getUsed() || (module.getStages().size() != 0) || module.getOutsourced()) {
            return createModule(companyIdentity, moduleCreateModel, module.getCompany());
        }

        module.setName(moduleCreateModel.getName());
        module.setPower(moduleCreateModel.getPower());
        module.setVegetables(moduleCreateModel.getVegetables());
        module.setResources(moduleCreateModel.getResources());
        module.setLeftovers(moduleCreateModel.getLeftovers());
        return moduleAssembler.getModelFromEntity(module);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public ModuleModel getModule(CompanyIdentity companyIdentity, Long moduleId) {
        return moduleAssembler.getModelFromEntity(getModuleEntity(moduleId));
    }

    public Module getModuleEntity(Long moduleId) {
        return moduleRepository.findById(moduleId).orElseThrow(() -> new ModuleNotFoundException(moduleId));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public String deleteModule(CompanyIdentity companyIdentity, Long moduleId) {
        Module module = getModuleEntity(moduleId);

        if(module.getUsed() || (module.getStages().size() != 0) || module.getOutsourced()){
            return "Module with id " + moduleId + " can not be deleted";
        }

        moduleRepository.delete(getModuleEntity(moduleId));
        return "Module with id " + moduleId + " deleted";
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ModuleModel> getCompanyModules(CompanyIdentity companyIdentity, Company company) {
        return moduleAssembler.getModelsFromEntityList(moduleRepository.findModulesByCompany(company));
    }

    public Company getCompanyByModuleId(Long moduleId) {
        return getModuleEntity(moduleId).getCompany();
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<StageModel> getModuleStages(CompanyIdentity companyIdentity, Long moduleId) {
        return stageAssembler.getModelsFromEntityList(getModuleEntity(moduleId).getStages());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<VegetableModel> getModuleVegetables(CompanyIdentity companyIdentity, Long moduleId) {
        return vegetableAssembler.getModelsFromEntityList(getModuleEntity(moduleId).getVegetables());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ResourceModel> getModuleResources(CompanyIdentity companyIdentity, Long moduleId) {
        return resourceAssembler.getModelsFromEntityList(getModuleEntity(moduleId).getResources());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LeftoverModel> getModuleLeftovers(CompanyIdentity companyIdentity, Long moduleId) {
        return leftoverAssembler.getModelsFromEntityList(getModuleEntity(moduleId).getLeftovers());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ModuleModel> getDefaultModules(CompanyIdentity companyIdentity, Company company) {
        return moduleAssembler.getModelsFromEntityList(moduleRepository.findModulesByOutsourced(true));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ModuleModel> getModulesByVegetableList(CompanyIdentity companyIdentity, List<VegetableModel> vegetableModels, Company company) {
        List<Vegetable> vegetables = vegetableAssembler.getEntityFromModelList(vegetableModels);
        List<Module> modules = new ArrayList<>();
        boolean init = true;
        for (Vegetable vegetable: vegetables){
            if (init){
                modules.addAll(moduleRepository.findModuleByVegetablesAndCompany(vegetable,company));
                modules.addAll(moduleRepository.findModuleByVegetablesAndOutsourced(vegetable,true));
                init = false;
            }else{
                List<Module> modulesTmp = new ArrayList<>();
                modulesTmp.addAll(moduleRepository.findModuleByVegetablesAndCompany(vegetable,company));
                modulesTmp.addAll(moduleRepository.findModuleByVegetablesAndOutsourced(vegetable,true));
                modules.retainAll(modulesTmp);
            }
        }
        return moduleAssembler.getModelsFromEntityList(modules);
    }
}
