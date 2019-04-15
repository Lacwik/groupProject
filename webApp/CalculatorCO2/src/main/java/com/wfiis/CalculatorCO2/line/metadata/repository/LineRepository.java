package com.wfiis.CalculatorCO2.line.metadata.repository;

import com.wfiis.CalculatorCO2.line.metadata.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Stage, Long> {
}
