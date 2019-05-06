package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.line.exceptions.LineNotFoundException;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.metadata.repository.LineRepository;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LineService {
    private final LineRepository lineRepository;
    private final LineAssembler lineAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LineModel> getCompanyLines(CompanyIdentity companyIdentity, Company company) {
        return lineAssembler.getModelsFromEntityList(lineRepository.findLinesByCompany(company));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public LineModel addLine(CompanyIdentity companyIdentity, LineCreateModel lineCreateModel, Company company){
        Line line = lineRepository.save(lineAssembler.getNewEntityFromModel(lineCreateModel, company));
        return lineAssembler.getModelFromEntity(line);
    }

    public Company getCompanyByLineId(Long lineId){
        return getLineById(lineId).getCompany();
    }

    public Line getLineById(Long lineId){
        return lineRepository.findById(lineId).orElseThrow(()->new LineNotFoundException(lineId));
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public LineModel editLine(CompanyIdentity companyIdentity, LineCreateModel lineCreateModel, Long lineId){
        Line line = getLineById(lineId);

        if (line.getUnused() == false)
            return addLine(companyIdentity, lineCreateModel, line.getCompany());

        line.setName(lineCreateModel.getName());
        line.setStages(lineCreateModel.getStages());
        line.setVegetables(lineCreateModel.getVegetables());
        return lineAssembler.getModelFromEntity(line);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineModel getLineModelById(CompanyIdentity companyIdentity, Long lineId){
        return lineAssembler.getModelFromEntity(getLineById(lineId));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public void deleteLineById(CompanyIdentity companyIdentity, Long lineId){
        lineRepository.delete(getLineById(lineId));
    }
}
