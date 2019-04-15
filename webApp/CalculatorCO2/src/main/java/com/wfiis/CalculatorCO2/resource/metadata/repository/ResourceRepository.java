package com.wfiis.CalculatorCO2.resource.metadata.repository;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
