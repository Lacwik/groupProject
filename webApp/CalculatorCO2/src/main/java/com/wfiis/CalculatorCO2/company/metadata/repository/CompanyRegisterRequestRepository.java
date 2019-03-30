package com.wfiis.CalculatorCO2.company.metadata.repository;

import com.wfiis.CalculatorCO2.company.metadata.entity.CompanyRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRegisterRequestRepository extends JpaRepository<CompanyRegisterRequest, Long> {
    List<CompanyRegisterRequest> findAllByIsAcceptedFalse();
}
