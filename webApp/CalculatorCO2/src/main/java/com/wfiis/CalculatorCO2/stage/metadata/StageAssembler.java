package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.ResourceFlagsAssembler;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.ResourceFlagsService;
import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
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

    private final ResourceFlagsAssembler resourceFlagsAssembler;

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
                stage.getPower(),
                stage.getLoss(),
                stage.getWaste(),
                stage.getVegetables(),
                stage.getModules()
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
                stage.getPower(),
                stage.getLoss(),
                stage.getWaste(),
                stage.getVegetables(),
                stage.getModules(),
                stage.getId()
        );
    }

    public Stage getNewEntityFromModel(StageCreateModel stageCreateModel, Company company){
        return new Stage(
                null,
                stageCreateModel.getName(),
                stageCreateModel.getPower(),
                stageCreateModel.getLoss(),
                stageCreateModel.getWaste(),
                resourceFlagsAssembler.getNewEntityFromModel(getFlags(stageCreateModel.getModules())),
                false,
                company,
                stageCreateModel.getVegetables(),
                stageCreateModel.getModules(),
                new ArrayList<>(),
                true
        );
    }

    public ResourceFlagsCreateModel getFlags(List<Module> modules) {
        ResourceFlagsCreateModel flags = new ResourceFlagsCreateModel();
        flags.setDieselFlag(false);
        flags.setFoilFlag(false);
        flags.setLpgFlag(false);
        flags.setWaterFlag(false);
        for (Module module : modules){
            flags.setDieselFlag(flags.getDieselFlag() || module.getResourceFlags().getDieselFlag());
            flags.setFoilFlag(flags.getFoilFlag() || module.getResourceFlags().getFoilFlag());
            flags.setLpgFlag(flags.getLpgFlag() || module.getResourceFlags().getLpgFlag());
            flags.setWaterFlag(flags.getWaterFlag() || module.getResourceFlags().getWaterFlag());
        }
        return flags;
    }

}
