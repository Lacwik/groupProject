package com.wfiis.CalculatorCO2.lineStatistics.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.lineStatistics.exceptions.LineStatisticsNotFoundException;
import com.wfiis.CalculatorCO2.lineStatistics.metadata.entity.LineStatistics;
import com.wfiis.CalculatorCO2.lineStatistics.metadata.repository.LineStatisticsRepository;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.StageResourceValueAssembler;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class LineStatisticsService {
    private final LineStatisticsAssembler lineStatisticsAssembler;
    private final LineStatisticsRepository lineStatisticsRepository;
    private final StageResourceValueAssembler stageResourceValueAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineStatisticsCreateModel createLineStatistics(CompanyIdentity companyIdentity, LineStatisticsCreateModel lineStatisticsCreateModel, Company company) {
        LineStatistics lineStatistics = lineStatisticsRepository.save(lineStatisticsAssembler.getNewEntityFromModel(lineStatisticsCreateModel));
        return lineStatisticsAssembler.getCreateModelFromEntity(lineStatistics);
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineStatisticsCreateModel editLineStatistics(CompanyIdentity companyIdentity, LineStatisticsCreateModel lineStatisticsCreateModel, Long lineStatisticsID) {
        LineStatistics lineStatistics = getLineStatisticsEntity(lineStatisticsID);

        lineStatistics.setLine(lineStatisticsCreateModel.getLine());
        lineStatistics.setStageResourceValues(stageResourceValueAssembler.getNewEntitiesFromModels(lineStatisticsCreateModel.getStageResourceValueCM()));
        return lineStatisticsAssembler.getCreateModelFromEntity(lineStatistics);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LineStatisticsCreateModel getLineStatistics(CompanyIdentity companyIdentity, Long lineStatisticsID) {
        return lineStatisticsAssembler.getCreateModelFromEntity(getLineStatisticsEntity(lineStatisticsID));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public String deleteLineStatistics(CompanyIdentity companyIdentity, Long lineStatisticsID) {
        LineStatistics lineStatistics = getLineStatisticsEntity(lineStatisticsID);

        lineStatisticsRepository.delete(lineStatistics);
        return "Module with id " + lineStatisticsID + " deleted";
    }


    public LineStatistics getLineStatisticsEntity(Long lineStatisticsID) {
        return lineStatisticsRepository.findById(lineStatisticsID).orElseThrow(() -> new LineStatisticsNotFoundException(lineStatisticsID));
    }
}
