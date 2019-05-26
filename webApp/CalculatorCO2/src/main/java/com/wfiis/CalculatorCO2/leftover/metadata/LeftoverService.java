package com.wfiis.CalculatorCO2.leftover.metadata;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.leftover.exceptions.LeftoverNotFoundException;
import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.leftover.metadata.repository.LeftoverRepository;
import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LeftoverService {
    private final LeftoverRepository leftoverRepository;
    private final LeftoverAssembler leftoverAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public LeftoverModel getLeftover(CompanyIdentity companyIdentity, Long leftoverId){
        return leftoverAssembler.getModelFromEntity(getLeftoverEntity(leftoverId));
    }

    public Leftover getLeftoverEntity(Long leftoverId){
        return leftoverRepository.findById(leftoverId).orElseThrow(()->new LeftoverNotFoundException(leftoverId));
    }

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<LeftoverModel> getAllModels(CompanyIdentity companyIdentity) {
        List<Leftover> leftovers = leftoverRepository.findAll();
        List<LeftoverModel> outLeftovers = new ArrayList<>();
        for (Leftover leftover : leftovers) {
            outLeftovers.add(leftoverAssembler.getModelFromEntity(leftover));
        }
        return outLeftovers;
    }
}
