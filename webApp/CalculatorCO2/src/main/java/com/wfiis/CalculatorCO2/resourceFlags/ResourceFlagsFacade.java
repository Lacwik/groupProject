package com.wfiis.CalculatorCO2.resourceFlags;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.ResourceFlagsService;
import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResourceFlagsFacade {
    private final ResourceFlagsService resourceFlagsService;
    private final UserMetadataService userMetadataService;

    public ResourceFlagsCreateModel createResourceFlags(ResourceFlagsCreateModel resourceFlagsCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return resourceFlagsService.addResourceFlags(CompanyIdentity.of(company.getId()), resourceFlagsCreateModel);
    }

    public ResourceFlagsCreateModel editResourceFlags(CompanyIdentity companyIdentity, ResourceFlagsCreateModel resourceFlagsCreateModel, Long resourceFlagsId){
        return resourceFlagsService.editResourceFlags(
                companyIdentity,
                resourceFlagsCreateModel,
                resourceFlagsId
        );
    }

    public void deleteResourceFlags(Long userId, Long resourceFlagsId){
        resourceFlagsService.deleteResourceFlagsById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                resourceFlagsId
        );
    }
}
