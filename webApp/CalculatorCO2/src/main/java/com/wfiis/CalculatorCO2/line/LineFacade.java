package com.wfiis.CalculatorCO2.line;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.line.metadata.LineService;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineFacade {
    private final LineService lineService;
    private final UserMetadataService userMetadataService;

    public LineModel createLine(LineCreateModel lineCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.createLine(CompanyIdentity.of(company.getId()), lineCreateModel, company);
    }

    public LineModel editLine(LineCreateModel lineCreateModel, Long lineId) {
        Company company = lineService.getCompanyByLineId(lineId);
        return lineService.editLine(CompanyIdentity.of(company.getId()), lineCreateModel, lineId);
    }

    public LineModel getLine(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getLine(CompanyIdentity.of(company.getId()), lineId);
    }

    public String deleteLine(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.deleteLine(CompanyIdentity.of(company.getId()), lineId);
    }

    public List<LineModel> getCompanyLines(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getCompanyLines(CompanyIdentity.of(company.getId()), company);
    }

    public List<StageModel> getLineStages(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getLineStages(CompanyIdentity.of(company.getId()), lineId);
    }

    public List<ModuleModel> getLineModules(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getLineModules(CompanyIdentity.of(company.getId()), lineId);
    }

    public List<VegetableModel> getLineVegetables(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getLineVegetables(CompanyIdentity.of(company.getId()), lineId);
    }

    public List<ResourceModel> getLineResources(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getLineResources(CompanyIdentity.of(company.getId()), lineId);
    }

    public List<LeftoverModel> getLineLeftovers(Long userId, Long lineId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getLineLeftovers(CompanyIdentity.of(company.getId()), lineId);
    }

    public List<LineModel> getDefaultLines(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getDefaultLines(CompanyIdentity.of(company.getId()), company);
    }
}
