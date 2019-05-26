package com.wfiis.CalculatorCO2.vegetable.model;

import lombok.Getter;

@Getter
public class VegetableModel extends VegetableCreateModel{
    private Long id;

    public VegetableModel(String name, long id){
        super(name);
        this.id = id;
    }
}
