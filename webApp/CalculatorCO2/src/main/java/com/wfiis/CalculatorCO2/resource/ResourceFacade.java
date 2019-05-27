package com.wfiis.CalculatorCO2.resource;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.resource.metadata.ResourceService;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResourceFacade {
    private final ResourceService resourceService;
    private final UserMetadataService userMetadataService;

    public ResourceModel getResource(Long userId, Long resourceId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return resourceService.getResource(CompanyIdentity.of(company.getId()), resourceId);
    }

    public List<ResourceModel> getAllResources(Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return resourceService.getAllModels(CompanyIdentity.of(company.getId()));
    }
}
