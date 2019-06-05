package com.wfiis.CalculatorCO2.lineStatistics;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.lineStatistics.metadata.LineStatisticsService;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineStatisticsFacade {
    private final LineStatisticsService lineStatisticsService;
    private final UserMetadataService userMetadataService;

    public LineStatisticsCreateModel createLineStatistics(LineStatisticsCreateModel lineStatisticsCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineStatisticsService.createLineStatistics(CompanyIdentity.of(company.getId()), lineStatisticsCreateModel, company);
    }

    public LineStatisticsCreateModel editLineStatistics(LineStatisticsCreateModel lineStatisticsCreateModel, Long lineStatisticsID, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineStatisticsService.editLineStatistics(CompanyIdentity.of(company.getId()), lineStatisticsCreateModel, lineStatisticsID);
    }

    public LineStatisticsCreateModel getLineStatistics(Long userId, Long lineStatisticsID) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineStatisticsService.getLineStatistics(CompanyIdentity.of(company.getId()), lineStatisticsID);
    }

    public String deleteLineStatistics(Long userId, Long lineStatisticsID) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineStatisticsService.deleteLineStatistics(CompanyIdentity.of(company.getId()), lineStatisticsID);
    }

    public List<LineStatisticsCreateModel> getStatisticsForCompany(CompanyIdentity companyIdentity) {
        return lineStatisticsService.getLineStatisticsForCompany(companyIdentity);

    }
}
