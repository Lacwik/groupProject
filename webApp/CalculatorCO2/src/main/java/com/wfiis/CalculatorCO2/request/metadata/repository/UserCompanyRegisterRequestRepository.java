package com.wfiis.CalculatorCO2.request.metadata.repository;

import com.wfiis.CalculatorCO2.request.metadata.entity.UserCompanyRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCompanyRegisterRequestRepository extends JpaRepository<UserCompanyRegisterRequest, Long> {
    List<UserCompanyRegisterRequest> findAllByIsAcceptedFalse();

    Optional<UserCompanyRegisterRequest> findByCompanyId(Long companyId);
}
