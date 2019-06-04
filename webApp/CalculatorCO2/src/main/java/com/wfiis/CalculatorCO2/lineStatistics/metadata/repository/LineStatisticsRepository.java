package com.wfiis.CalculatorCO2.lineStatistics.metadata.repository;

import com.wfiis.CalculatorCO2.lineStatistics.metadata.entity.LineStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineStatisticsRepository extends JpaRepository<LineStatistics, Long> {
}
