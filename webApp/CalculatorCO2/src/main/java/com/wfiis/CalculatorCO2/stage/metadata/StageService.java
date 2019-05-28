package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.metadata.LeftoverAssembler;
import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.line.metadata.LineAssembler;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.module.metadata.ModuleAssembler;
import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.metadata.ResourceAssembler;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.exceptions.StageNotFoundException;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.metadata.repository.StageRepository;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableAssembler;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StageService {
    private final StageRepository stageRepository;
    private final StageAssembler stageAssembler;
    private final LineAssembler lineAssembler;
    private final ModuleAssembler moduleAssembler;
    private final ModuleService moduleService;
    private final VegetableAssembler vegetableAssembler;
    private final ResourceAssembler resourceAssembler;
    private final LeftoverAssembler leftoverAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageModel createStage(CompanyIdentity companyIdentity, StageCreateModel stageCreateModel, Company company) {
        Stage stage = stageRepository.save(stageAssembler.getNewEntityFromCreateModel(stageCreateModel, company));
        return stageAssembler.getModelFromEntity(stage);
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageModel editStage(CompanyIdentity companyIdentity, StageCreateModel stageCreateModel, Long stageId) {
        Stage stage = getStageEntity(stageId);

        if (stage.getUsed()) {
            return createStage(companyIdentity, stageCreateModel, stage.getCompany());
        }
        List<Module> modules = new ArrayList<>();

        for (ModuleModel moduleModel : stageCreateModel.getModulesModels())
            modules.add(moduleService.getModuleEntity(moduleModel.getId()));

        stage.setName(stageCreateModel.getName());
        stage.setModules(modules);
        return stageAssembler.getModelFromEntity(stage);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageModel getStage(CompanyIdentity companyIdentity, Long stageId) {
        return stageAssembler.getModelFromEntity(getStageEntity(stageId));
    }

    public Stage getStageEntity(Long stageId) {
        return stageRepository.findById(stageId).orElseThrow(() -> new StageNotFoundException(stageId));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public String deleteStage(CompanyIdentity companyIdentity, Long stageId) {
        Stage stage = getStageEntity(stageId);

        if (stage.getUsed() || stage.getLines().size() != 0) {
            return "Stage with id " + stageId + " can not be deleted";
        }

        stageRepository.delete(getStageEntity(stageId));
        return "Stage with id " + stageId + " deleted";
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<StageModel> getCompanyStages(CompanyIdentity companyIdentity, Company company) {
        return stageAssembler.getModelsFromEntityList(stageRepository.findStagesByCompany(company));
    }

    public Company getCompanyByStageId(Long stageId) {
        return getStageEntity(stageId).getCompany();
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LineModel> getStageLines(CompanyIdentity companyIdentity, Long stageId) {
        return lineAssembler.getModelsFromEntityList(getStageEntity(stageId).getLines());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ModuleModel> getStageModules(CompanyIdentity companyIdentity, Long stageId) {
        return moduleAssembler.getModelsFromEntityList(getStageEntity(stageId).getModules());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<VegetableModel> getStageVegetables(CompanyIdentity companyIdentity, Long stageId) {
        List<ModuleModel> moduleModels = getStageModules(companyIdentity, stageId);

        List<VegetableModel> vegetableModels = new ArrayList<>();

        boolean init = true;
        for (ModuleModel moduleModel : moduleModels) {
            List<VegetableModel> vegetableModelsTmp = new ArrayList<>();

            List<Vegetable> vegetables = moduleModel.getVegetables();
            for (Vegetable vegetable : vegetables) {
                if (init) {
                    vegetableModels.add(vegetableAssembler.getModelFromEntity(vegetable));
                } else {
                    vegetableModelsTmp.add(vegetableAssembler.getModelFromEntity(vegetable));
                }
            }
            if (init) {
                init = false;
            } else {
                vegetableModels.retainAll(vegetableModelsTmp);
            }
        }

        return vegetableModels;
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ResourceModel> getStageResources(CompanyIdentity companyIdentity, Long stageId) {
        List<ModuleModel> moduleModels = getStageModules(companyIdentity, stageId);

        Set<ResourceModel> resourceModelSet = new HashSet<>();

        for (ModuleModel moduleModel : moduleModels) {
            List<Resource> resources = moduleModel.getResources();
            for (Resource resource : resources) {
                resourceModelSet.add(resourceAssembler.getModelFromEntity(resource));
            }
        }

        return new ArrayList<>(resourceModelSet);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LeftoverModel> getStageLeftovers(CompanyIdentity companyIdentity, Long stageId) {
        List<ModuleModel> moduleModels = getStageModules(companyIdentity, stageId);

        Set<LeftoverModel> leftoverModelSet = new HashSet<>();

        for (ModuleModel moduleModel : moduleModels) {
            List<Leftover> leftovers = moduleModel.getLeftovers();
            for (Leftover leftover : leftovers) {
                leftoverModelSet.add(leftoverAssembler.getModelFromEntity(leftover));
            }
        }

        return new ArrayList<>(leftoverModelSet);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<StageModel> getDefaultStages(CompanyIdentity companyIdentity, Company company) {
        return stageAssembler.getModelsFromEntityList(stageRepository.findStagesByOutsourced(true));
    }
}
