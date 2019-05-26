package com.wfiis.CalculatorCO2.leftover.model;

import lombok.Getter;

@Getter
public class LeftoverModel extends LeftoverCreateModel {
    private Long id;

    public LeftoverModel(String name, long id){
        super(name);
        this.id = id;
    }
}
