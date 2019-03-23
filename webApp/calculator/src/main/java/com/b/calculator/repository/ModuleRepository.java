package com.b.calculator.repository;

import com.b.calculator.model.Company;
import com.b.calculator.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findModulesByCompany(Company company);
}
