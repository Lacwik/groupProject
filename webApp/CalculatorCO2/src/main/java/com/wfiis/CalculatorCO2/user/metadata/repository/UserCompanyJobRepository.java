package com.wfiis.CalculatorCO2.user.metadata.repository;

import com.wfiis.CalculatorCO2.user.metadata.entity.UserCompanyJob;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompanyJobRepository extends JpaRepository<UserCompanyJob, Long> {
    void deleteAllByUserId(Long userId);
    boolean existsByUserIdAndCompanyIdAndRole(Long userId, Long companyId, CompanyRole companyRole);
    boolean existsByUserIdAndCompanyId(Long userId, Long companyId);
}
