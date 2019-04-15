package com.wfiis.CalculatorCO2.vegetable.metadata.repository;

import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
}
