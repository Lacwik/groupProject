package com.wfiis.CalculatorCO2.stageResourceValue;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.StageResourceValueService;
import com.wfiis.CalculatorCO2.stageResourceValue.model.StageResourceValueCreateModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageResourceValueFacade {
    private final StageResourceValueService stageResourceValueService;
    private final UserMetadataService userMetadataService;

    public StageResourceValueCreateModel createStageResourceValue(StageResourceValueCreateModel stageResourceValueCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageResourceValueService.createStageResourceValue(CompanyIdentity.of(company.getId()), stageResourceValueCreateModel, company);
    }

    public StageResourceValueCreateModel editStageResourceValue(StageResourceValueCreateModel moduleCreateModel, Long srvId, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageResourceValueService.editStageResourceValue(CompanyIdentity.of(company.getId()), moduleCreateModel, srvId);
    }

    public StageResourceValueCreateModel getStageResourceValue(Long userId, Long srvId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageResourceValueService.getStageResourceValue(CompanyIdentity.of(company.getId()), srvId);
    }

    public String deleteStageResourceValue(Long userId, Long srvId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return stageResourceValueService.deleteStageResourceValue(CompanyIdentity.of(company.getId()), srvId);
    }
}
