package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.metadata.repository.LineRepository;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LineService {
    private final LineRepository lineRepository;
    private final LineAssembler lineAssembler;
    //private final StageService stageService;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LineModel> getCompanyLines(CompanyIdentity companyIdentity, Company company) {
        return lineAssembler.getModelsFromEntityList(lineRepository.findLinesByCompany(company));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public LineModel addLine(CompanyIdentity companyIdentity, LineCreateModel lineCreateModel, Company company){
        Line line = lineRepository.save(lineAssembler.getNewEntityFromModel(lineCreateModel, company));
        return lineAssembler.getModelFromEntity(line);
    }

    public Company getCompanyByLineId(Long stageId){
        return getStageById(stageId).getCompany();
    }

    public Line getStageById(Long stageId){
        return lineRepository.findById(stageId).orElseThrow(()->new LineNotFoundException(lineId));
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public LineModel editLine(CompanyIdentity companyIdentity, LineCreateModel stageCreateModel, Long lineId){
        Line line = getLineById(lineId);
        line.setName(stageCreateModel.getName());
        line.setStages(stageCreateModel.getStages());
        line.setVegetables(stageCreateModel.getVegetables());
        return lineAssembler.getModelFromEntity(line);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineModel getLineModelById(CompanyIdentity companyIdentity, Long lineId){
        return lineAssembler.getModelFromEntity(getLineById(lineId));
    }
}
