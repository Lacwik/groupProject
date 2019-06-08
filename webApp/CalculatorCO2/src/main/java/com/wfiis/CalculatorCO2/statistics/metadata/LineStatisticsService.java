package com.wfiis.CalculatorCO2.statistics.metadata;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.LineStatistics;
import com.wfiis.CalculatorCO2.statistics.metadata.repository.LineStatisticsRepository;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineStatisticsService {
    private final LineStatisticsRepository lineStatisticsRepository;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LineStatistics> getLineStatisticsForCompany(CompanyIdentity companyIdentity) {
        return lineStatisticsRepository.findAllByCompanyId(companyIdentity.getCompanyId());
    }

    public LineStatistics createStatistic(LineStatistics lineStatistics) {
        return lineStatisticsRepository.save(lineStatistics);
    }
}
