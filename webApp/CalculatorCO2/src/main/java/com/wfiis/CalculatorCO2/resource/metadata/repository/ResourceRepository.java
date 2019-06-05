package com.wfiis.CalculatorCO2.resource.metadata.repository;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAll();
    Resource findByGus(String gusId);
}
