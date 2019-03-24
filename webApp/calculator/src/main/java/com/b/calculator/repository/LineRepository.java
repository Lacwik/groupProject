package com.b.calculator.repository;

import com.b.calculator.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    List<Line> findLinesByCompany(Company company);
}
