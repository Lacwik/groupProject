package com.wfiis.CalculatorCO2.resource.metadata;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.resource.exceptions.ResourceNotFoundException;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.resource.metadata.repository.ResourceRepository;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResourceService {
    ResourceRepository resourceRepository;
    ResourceAssembler resourceAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public ResourceModel getResource(CompanyIdentity companyIdentity, Long resourceId){
        return resourceAssembler.getModelFromEntity(getResourceEntity(resourceId));
    }

    public Resource getResourceEntity(Long resourceId){
        return resourceRepository.findById(resourceId).orElseThrow(()->new ResourceNotFoundException(resourceId));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<ResourceModel> getAllModels(CompanyIdentity companyIdentity) {
        List<Resource> resources = resourceRepository.findAll();
        List<ResourceModel> outResources = new ArrayList<>();
        for (Resource resource : resources) {
            outResources.add(resourceAssembler.getModelFromEntity(resource));
        }
        return outResources;
    }
}
