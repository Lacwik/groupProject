package com.wfiis.CalculatorCO2.vegetable.metadata;

import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.user.model.CompanyRole;
import com.wfiis.CalculatorCO2.user.security.scopes.SecureCompanyScope;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import com.wfiis.CalculatorCO2.vegetable.metadata.repository.VegetableRepository;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;

import java.util.ArrayList;
import java.util.List;

public class VegetableService {
    VegetableRepository vegetableRepository;
    VegetableAssembler vegetableAssembler;

    @SecureCompanyScope(role = CompanyRole.MEMBER)
    public List<VegetableModel> getAllModels(CompanyIdentity companyIdentity){
        List<Vegetable> vegetables = vegetableRepository.findAll();
        List<VegetableModel> outVegetables = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            outVegetables.add(vegetableAssembler.getModelFromEntity(vegetable));
        }
        return outVegetables;
    }
}
