package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.metadata.LeftoverAssembler;
import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.line.exceptions.LineNotFoundException;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.metadata.repository.LineRepository;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.module.metadata.ModuleAssembler;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.metadata.ResourceAssembler;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LineService {
    private final LineRepository lineRepository;
    private final LineAssembler lineAssembler;
    private final StageAssembler stageAssembler;
    private final StageService stageService;
    private final ModuleAssembler moduleAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineModel createLine(CompanyIdentity companyIdentity, LineCreateModel lineCreateModel, Company company){
        Line line = lineRepository.save(lineAssembler.getNewEntityFromModel(lineCreateModel, company));
        return lineAssembler.getModelFromEntity(line);
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineModel editLine(CompanyIdentity companyIdentity, LineCreateModel lineCreateModel, Long lineId){
        Line line = getLineEntity(lineId);

        if (line.getUsed()){
            return createLine(companyIdentity, lineCreateModel, line.getCompany());
        }

        List<Stage> stages = new ArrayList<>();

        for (StageModel stageModel : lineCreateModel.getStageModels())
            stages.add(stageAssembler.getEntity(stageModel.getId()));

        line.setName(lineCreateModel.getName());
        line.setStages(stages);
        return lineAssembler.getModelFromEntity(line);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineModel getLine(CompanyIdentity companyIdentity, Long lineId){
        return lineAssembler.getModelFromEntity(getLineEntity(lineId));
    }

    public Line getLineEntity(Long lineId){
        return lineRepository.findById(lineId).orElseThrow(()->new LineNotFoundException(lineId));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public String deleteLine(CompanyIdentity companyIdentity, Long lineId){
        Line line = getLineEntity(lineId);

        if(line.getUsed()){
            return "Line with id " + lineId + " can not be deleted";
        }

        lineRepository.delete(getLineEntity(lineId));
        return "Line with id " + lineId + " deleted";
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LineModel> getCompanyLines(CompanyIdentity companyIdentity, Company company) {
        return lineAssembler.getModelsFromEntityList(lineRepository.findLinesByCompany(company));
    }

    public Company getCompanyByLineId(Long lineId){
        return getLineEntity(lineId).getCompany();
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<StageModel> getLineStages(CompanyIdentity companyIdentity, Long lineId){
        return stageAssembler.getModelsFromEntityList(getLineEntity(lineId).getStages());
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ModuleModel> getLineModules(CompanyIdentity companyIdentity, Long lineId){
        List<StageModel> stageModels = getLineStages(companyIdentity, lineId);

        List<ModuleModel> moduleModels = new ArrayList<>();

        for(StageModel stageModel : stageModels){
            List<ModuleModel> models = stageModel.getModulesModels();
            for(ModuleModel module : models){
                moduleModels.add(module);
            }
        }

        return moduleModels;
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<VegetableModel> getLineVegetables(CompanyIdentity companyIdentity, Long lineId) {
        List<StageModel> stageModels = getLineStages(companyIdentity, lineId);

        List<VegetableModel> vegetableModels = new ArrayList<>();

        boolean init = true;
        for(StageModel stageModel : stageModels){
            List<VegetableModel> vegetableModelsTmp = new ArrayList<>();

            if (init) {
                vegetableModels.addAll(stageService.getStageVegetables(companyIdentity, stageModel.getId()));
                init = false;
            } else {
                vegetableModelsTmp.addAll(stageService.getStageVegetables(companyIdentity, stageModel.getId()));
                vegetableModels.retainAll(vegetableModelsTmp);
            }
        }

        return vegetableModels;
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ResourceModel> getLineResources(CompanyIdentity companyIdentity, Long lineId) {
        List<StageModel> stageModels = getLineStages(companyIdentity, lineId);
        Set<ResourceModel> resourceModelSet = new HashSet<>();

        for(StageModel stageModel : stageModels){
            resourceModelSet.addAll(stageService.getStageResources(companyIdentity, stageModel.getId()));
        }

        return new ArrayList<>(resourceModelSet);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LeftoverModel> getLineLeftovers(CompanyIdentity companyIdentity, Long lineId) {
        List<StageModel> stageModels = getLineStages(companyIdentity, lineId);
        Set<LeftoverModel> leftoverModelSet = new HashSet<>();

        for(StageModel stageModel : stageModels){
            leftoverModelSet.addAll(stageService.getStageLeftovers(companyIdentity, stageModel.getId()));
        }

        return new ArrayList<>(leftoverModelSet);
    }
}
