package com.wfiis.CalculatorCO2.line.metadata.repository;

import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    List<Line> findLinesByOutsourced(int outsourced);
    List<Line> findLinesByCompanyId(Long companyId);
}
