package com.wfiis.CalculatorCO2.stage;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StageFacade {
    private final StageService stageService;
    private final UserMetadataService userMetadataService;

    public StageModel createStage(StageCreateModel stageCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.createStage(CompanyIdentity.of(company.getId()), stageCreateModel, company);
    }

    public StageModel editStage(StageCreateModel stageCreateModel, Long stageId) {
        Company company = stageService.getCompanyByStageId(stageId);
        return stageService.editStage(CompanyIdentity.of(company.getId()), stageCreateModel, stageId);
    }

    public StageModel getStage(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStage(CompanyIdentity.of(company.getId()), stageId);
    }

    public String deleteStage(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.deleteStage(CompanyIdentity.of(company.getId()), stageId);
    }

    public List<StageModel> getCompanyStages(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getCompanyStages(CompanyIdentity.of(company.getId()), company);
    }

    public List<LineModel> getStageLines(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStageLines(CompanyIdentity.of(company.getId()), stageId);
    }

    public List<ModuleModel> getStageModules(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStageModules(CompanyIdentity.of(company.getId()), stageId);
    }

    public List<VegetableModel> getStageVegetables(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStageVegetables(CompanyIdentity.of(company.getId()), stageId);
    }

    public List<ResourceModel> getStageResources(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStageResources(CompanyIdentity.of(company.getId()), stageId);
    }

    public List<LeftoverModel> getStageLeftovers(Long userId, Long stageId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStageLeftovers(CompanyIdentity.of(company.getId()), stageId);
    }

    public List<StageModel> getDefaultStages(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getDefaultStages(CompanyIdentity.of(company.getId()), company);
    }

    public List<StageModel> getStagesByVegetableList(List<VegetableModel> vegetableModels, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStagesByVegetableList(CompanyIdentity.of(company.getId()), vegetableModels, company);
    }
}
