package com.wfiis.CalculatorCO2.vegetable;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VegetableFacade {
    private final VegetableService vegetableService;
    private final UserMetadataService userMetadataService;

    public List<VegetableModel> getAllVegetables(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return vegetableService.getAllModels(CompanyIdentity.of(company.getId()));
    }
}
