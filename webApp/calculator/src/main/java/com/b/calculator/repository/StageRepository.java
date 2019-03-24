package com.b.calculator.repository;

import com.b.calculator.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findStagesByCompany(Company company);

    List<Stage> findStagesByOutsourced(Long outsorced);
}
