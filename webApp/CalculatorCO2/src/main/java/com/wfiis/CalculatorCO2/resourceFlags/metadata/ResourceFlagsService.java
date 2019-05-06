package com.wfiis.CalculatorCO2.resourceFlags.metadata;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.resourceFlags.exceptions.ResourceFlagsNotFoundException;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.entity.ResourceFlags;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.repository.ResourceFlagsRepository;
import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ResourceFlagsService {
    private final ResourceFlagsAssembler resourceFlagsAssembler;
    private final ResourceFlagsRepository resourceFlagsRepository;

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public ResourceFlagsCreateModel addResourceFlags(CompanyIdentity companyIdentity, ResourceFlagsCreateModel resourceFlagsCreateModel){
        ResourceFlags resourceFlags = resourceFlagsRepository.save(resourceFlagsAssembler.getNewEntityFromModel(resourceFlagsCreateModel));
        return resourceFlagsAssembler.getCreateModelFromEntity(resourceFlags);
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public ResourceFlagsCreateModel editResourceFlags(CompanyIdentity companyIdentity, ResourceFlagsCreateModel resourceFlagsCreateModel, Long resourceFlagsId){
        ResourceFlags resourceFlags = getResourceFlagsById(resourceFlagsId);

        resourceFlags.setWaterFlag(resourceFlagsCreateModel.getWaterFlag());
        resourceFlags.setLpgFlag(resourceFlagsCreateModel.getLpgFlag());
        resourceFlags.setFoilFlag(resourceFlagsCreateModel.getFoilFlag());
        resourceFlags.setDieselFlag(resourceFlagsCreateModel.getDieselFlag());
        resourceFlagsRepository.save(resourceFlags);
        return resourceFlagsAssembler.getCreateModelFromEntity(resourceFlags);
    }

    public ResourceFlags getResourceFlagsById(Long resourceFlagsId){
        return resourceFlagsRepository.findById(resourceFlagsId).orElseThrow(()->new ResourceFlagsNotFoundException(resourceFlagsId));
    }

    @SecureCompanyScope(role = CompanyRole.ADMIN)
    public void deleteResourceFlagsById(CompanyIdentity companyIdentity, Long resourceFlagsId){
        resourceFlagsRepository.delete(getResourceFlagsById(resourceFlagsId));
    }
}
