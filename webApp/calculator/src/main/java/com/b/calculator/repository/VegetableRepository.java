package com.b.calculator.repository;

import com.b.calculator.model.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
}
