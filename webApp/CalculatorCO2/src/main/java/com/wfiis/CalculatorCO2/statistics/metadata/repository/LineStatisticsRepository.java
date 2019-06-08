package com.wfiis.CalculatorCO2.statistics.metadata.repository;

import com.wfiis.CalculatorCO2.statistics.metadata.entity.LineStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineStatisticsRepository extends JpaRepository<LineStatistics, Long> {
    List<LineStatistics> findAllByCompanyId(Long companyId);
}
