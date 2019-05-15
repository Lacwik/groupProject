package com.wfiis.CalculatorCO2.leftover.metadata.repository;


import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeftoverRepository extends JpaRepository<Leftover, Long> {
    List<Leftover> findAll();
}
