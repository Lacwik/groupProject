package com.wfiis.CalculatorCO2.stageResourceValue.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.stageResourceValue.exceptions.StageResourceValueNotFoundException;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.entity.StageResourceValue;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.repository.StageResourceValueRepository;
import com.wfiis.CalculatorCO2.stageResourceValue.model.StageResourceValueCreateModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class StageResourceValueService {
    private final StageResourceValueRepository stageResourceValueRepository;
    private final StageResourceValueAssembler stageResourceValueAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageResourceValueCreateModel createStageResourceValue(CompanyIdentity companyIdentity, StageResourceValueCreateModel stageResourceValueCreateModel, Company company) {
        StageResourceValue stageResourceValue = stageResourceValueRepository.save(stageResourceValueAssembler.getNewEntityFromModel(stageResourceValueCreateModel));
        return stageResourceValueAssembler.getCreateModelFromEntity(stageResourceValue);
    }

    @Transactional
    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageResourceValueCreateModel editStageResourceValue(CompanyIdentity companyIdentity, StageResourceValueCreateModel stageResourceValueCreateModel, Long srvId) {
        StageResourceValue stageResourceValue = getStageResourceValueEntity(srvId);

        stageResourceValue.setResource(stageResourceValueCreateModel.getResource());
        stageResourceValue.setValue(stageResourceValueCreateModel.getValue());
        stageResourceValue.setStage(stageResourceValueCreateModel.getStage());
        stageResourceValue.setTime(stageResourceValueCreateModel.getTime());
        return stageResourceValueAssembler.getCreateModelFromEntity(stageResourceValue);
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public StageResourceValueCreateModel getStageResourceValue(CompanyIdentity companyIdentity, Long srvId) {
        return stageResourceValueAssembler.getCreateModelFromEntity(getStageResourceValueEntity(srvId));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public String deleteStageResourceValue(CompanyIdentity companyIdentity, Long srvId) {
        StageResourceValue stageResourceValue = getStageResourceValueEntity(srvId);

        stageResourceValueRepository.delete(stageResourceValue);
        return "Module with id " + srvId + " deleted";
    }


    public StageResourceValue getStageResourceValueEntity(Long srvID) {
        return stageResourceValueRepository.findById(srvID).orElseThrow(() -> new StageResourceValueNotFoundException(srvID));
    }
}
