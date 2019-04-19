package com.wfiis.CalculatorCO2.stage.metadata;

import com.wfiis.CalculatorCO2.company.metadata.CompanyService;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.module.metadata.ModuleService;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.stage.metadata.repository.StageRepository;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class StageService {
    private StageRepository stageRepository;
    private CompanyService companyService;
    private ModuleService moduleService;
    private VegetableService vegetableService;
    private StageAssembler stageAssembler;

    public List<Stage> getOutsourcedStages() {
        return stageRepository.findStagesByOutsourced(true);
    }

    public List<Stage> getCompanyStages(Long companyId) {
        return stageRepository.findStagesByCompanyId(companyId);
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public StageModel addModel(CompanyIdentity companyIdentity, StageCreateModel stageCreateModel){
        Company company = companyService.findCompany(companyIdentity.getCompanyId());
        Stage stage = stageRepository.save(stageAssembler.getNewEntityFromModel(stageCreateModel, company));
        return stageAssembler.getModelFromEntity(stage);
    }

}
