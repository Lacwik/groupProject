package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.module.metadata.ModuleAssembler;
import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StageAssembler {
    private final ModuleAssembler moduleAssembler;

    public List<StageCreateModel> getCreateModelsFromEntityList(List<Stage> stages) {
        List<StageCreateModel> outStages = new ArrayList<>();
        for (Stage stage : stages) {
            outStages.add(getCreateModelFromEntity(stage));
        }
        return outStages;
    }

    public StageCreateModel getCreateModelFromEntity(Stage stage) {
        return new StageCreateModel(
                stage.getName(),
                moduleAssembler.getModelsFromEntityList(stage.getModules())
        );
    }

    public List<StageModel> getModelsFromEntityList(List<Stage> stages) {
        List<StageModel> outStages = new ArrayList<>();
        for (Stage stage : stages) {
            outStages.add(getModelFromEntity(stage));
        }
        return outStages;
    }

    public StageModel getModelFromEntity(Stage stage) {
        return new StageModel(
                stage.getName(),
                moduleAssembler.getModelsFromEntityList(stage.getModules()),
                stage.getId()
        );
    }

    public Stage getNewEntityFromCreateModel(StageCreateModel stageCreateModel, Company company){
        List<Module> modules = new ArrayList<>();

        for (ModuleModel moduleModel : stageCreateModel.getModulesModels())
            modules.add(moduleAssembler.getEntity(moduleModel.getId()));

        return new Stage(
                null,
                stageCreateModel.getName(),
                false,
                false,
                company,
                new ArrayList<>(),
                modules
        );
    }
}
