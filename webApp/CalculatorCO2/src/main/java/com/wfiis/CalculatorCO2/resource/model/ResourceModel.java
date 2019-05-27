package com.wfiis.CalculatorCO2.resource.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ResourceModel extends ResourceCreateModel{
    private Long id;

    public ResourceModel(String name, String gus, long id){
        super(name, gus);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceModel that = (ResourceModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
