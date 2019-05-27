package com.wfiis.CalculatorCO2.vegetable.metadata.repository;

import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
    List<Vegetable> findAll();
}
