package com.wfiis.CalculatorCO2.stage.metadata.repository;

import com.wfiis.CalculatorCO2.stage.metadata.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Module, Long> {
}
