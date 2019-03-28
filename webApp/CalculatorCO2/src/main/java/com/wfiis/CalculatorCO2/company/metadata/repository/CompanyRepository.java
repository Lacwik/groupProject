package com.wfiis.CalculatorCO2.company.metadata.repository;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
