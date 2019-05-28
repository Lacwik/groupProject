package com.wfiis.CalculatorCO2.stage.metadata.repository;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findStagesByOutsourced(Boolean outsourced);
    List<Stage> findStagesByCompany(Company company);
}
