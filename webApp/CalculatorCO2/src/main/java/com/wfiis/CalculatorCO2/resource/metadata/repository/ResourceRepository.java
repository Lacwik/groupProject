package com.wfiis.CalculatorCO2.resource.metadata.repository;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAll();
}
