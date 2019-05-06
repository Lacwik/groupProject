package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.line.metadata.LineAssembler;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.ResourceFlagsService;
import com.wfiis.CalculatorCO2.stage.exceptions.StageNotFoundException;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.metadata.repository.StageRepository;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StageService {
    private final StageRepository stageRepository;
    private final StageAssembler stageAssembler;
    private final LineAssembler lineAssembler;
    private final ResourceFlagsService resourceFlagsService;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<StageModel> getCompanyStages(CompanyIdentity companyIdentity, Company company) {
        return stageAssembler.getModelsFromEntityList(stageRepository.findStagesByCompany(company));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public StageModel addStage(CompanyIdentity companyIdentity, StageCreateModel stageCreateModel, Company company){
        Stage stage = stageRepository.save(stageAssembler.getNewEntityFromModel(stageCreateModel, company));
        return stageAssembler.getModelFromEntity(stage);
    }

    public Company getCompanyByStageId(Long stageId){
        return getStageById(stageId).getCompany();
    }

    public Stage getStageById(Long stageId){
        return stageRepository.findById(stageId).orElseThrow(()->new StageNotFoundException(stageId));
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public StageModel editStage(CompanyIdentity companyIdentity, StageCreateModel stageCreateModel, Long stageId){
        Stage stage = getStageById(stageId);

        if (stage.getUnused() == false)
            return addStage(companyIdentity, stageCreateModel, stage.getCompany());

        resourceFlagsService.editResourceFlags(
                companyIdentity,
                stageAssembler.getFlags(stage.getModules()),
                stage.getResourceFlags().getId()
        );

        stage.setName(stageCreateModel.getName());
        stage.setPower(stageCreateModel.getPower());
        stage.setLoss(stageCreateModel.getLoss());
        stage.setWaste(stageCreateModel.getWaste());
        stage.setVegetables(stageCreateModel.getVegetables());
        stage.setModules(stageCreateModel.getModules());
        return stageAssembler.getModelFromEntity(stage);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageModel getStageModelById(CompanyIdentity companyIdentity, Long stageId){
        return stageAssembler.getModelFromEntity(getStageById(stageId));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public List<LineModel> getStageLinesById(CompanyIdentity companyIdentity, Long stageId){
        return lineAssembler.getModelsFromEntityList(getStageById(stageId).getLines());
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public void deleteStageById(CompanyIdentity companyIdentity, Long stageId){
        stageRepository.delete(getStageById(stageId));
    }
}
