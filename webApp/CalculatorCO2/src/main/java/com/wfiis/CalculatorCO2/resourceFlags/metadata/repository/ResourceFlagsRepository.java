package com.wfiis.CalculatorCO2.resourceFlags.metadata.repository;

import com.wfiis.CalculatorCO2.resourceFlags.metadata.entity.ResourceFlags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceFlagsRepository extends JpaRepository<ResourceFlags, Long> {
}
