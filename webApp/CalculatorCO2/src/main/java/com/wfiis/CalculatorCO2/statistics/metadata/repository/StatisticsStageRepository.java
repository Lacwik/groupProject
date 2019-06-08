package com.wfiis.CalculatorCO2.statistics.metadata.repository;

import com.wfiis.CalculatorCO2.statistics.metadata.entity.StatisticsStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsStageRepository extends JpaRepository<StatisticsStage, Long> {
}
