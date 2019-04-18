package com.wfiis.CalculatorCO2.stage;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StageFacade {
    private StageService stageService;

    public List<StageModel> getOutsourcedStages(){
        return stageService.getModelsFromEntityList(
                stageService.getOutsourcedStages()
        );
    }

    public List<StageModel> getCompanyStages(Long companyId){
        return stageService.getModelsFromEntityList(
                stageService.getCompanyStages(companyId)
        );
    }
}
