package com.wfiis.CalculatorCO2.statistics;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.statistics.metadata.LineStatisticsService;
import com.wfiis.CalculatorCO2.statistics.metadata.entity.LineStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineStatisticsFacade {
    private final LineStatisticsService lineStatisticsService;

    public List<LineStatistics> getStatisticsForCompany(CompanyIdentity companyIdentity) {
        return lineStatisticsService.getLineStatisticsForCompany(companyIdentity);

    }
}
