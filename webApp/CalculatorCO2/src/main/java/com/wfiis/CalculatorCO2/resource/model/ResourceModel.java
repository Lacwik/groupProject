package com.wfiis.CalculatorCO2.resource.model;

import lombok.Getter;

@Getter
public class ResourceModel extends ResourceCreateModel{
    private Long id;

    public ResourceModel(String name, String gus, long id){
        super(name, gus);
        this.id = id;
    }
}
