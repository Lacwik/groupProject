package com.wfiis.CalculatorCO2.stage;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageFacade {
    private StageService stageService;
    private UserMetadataService userMetadataService;

    /*public List<StageCreateModel> getOutsourcedStages(){
        return stageService.getModelsFromEntityList(
                stageService.getOutsourcedStages()
        );
    }

    public List<StageCreateModel> getCompanyStages(Long companyId){
        return stageService.getModelsFromEntityList(
                stageService.getCompanyStages(companyId)
        );
    }*/


    public StageModel createStage(StageCreateModel stageCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageService.addModel(CompanyIdentity.of(company.getId()), stageCreateModel);
    }
}
