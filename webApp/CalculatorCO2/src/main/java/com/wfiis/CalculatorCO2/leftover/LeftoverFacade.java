package com.wfiis.CalculatorCO2.leftover;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.metadata.LeftoverService;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LeftoverFacade {
    private final LeftoverService leftoverService;
    private final UserMetadataService userMetadataService;

    public LeftoverModel getLeftover(Long userId, Long leftoverId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return leftoverService.getLeftover(CompanyIdentity.of(company.getId()), leftoverId);
    }

    public List<LeftoverModel> getAllLeftovers(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return leftoverService.getAllModels(CompanyIdentity.of(company.getId()));
    }
}
