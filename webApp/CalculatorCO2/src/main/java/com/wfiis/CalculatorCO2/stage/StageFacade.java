package com.wfiis.CalculatorCO2.stage;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
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
        return stageService.addStage(CompanyIdentity.of(company.getId()), stageCreateModel, company);
    }

    public StageModel editStage(StageCreateModel stageCreateModel, Long stageId){
        return stageService.editStage(
                CompanyIdentity.of(
                        stageService.getCompanyByStageId(stageId).getId()
                ),
                stageCreateModel,
                stageId
        );
    }

    public List<StageModel> getCompanyStages(Long userId){
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getCompanyStages(CompanyIdentity.of(company.getId()), company);
    }

    public StageModel getStage(Long userId, Long stageId){
        return stageService.getStageModelById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                stageId
        );
    }

    public List<LineModel> getStageLinesById(Long userId, Long stageId){
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.getStageLinesById(CompanyIdentity.of(company.getId()), stageId);
    }

    public void deleteStage(Long userId, Long stageId){
        stageService.deleteStageById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                stageId
        );
    }
}
