package com.wfiis.CalculatorCO2.vegetable.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class VegetableModel extends VegetableCreateModel{
    private Long id;

    public VegetableModel(String name, long id){
        super(name);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VegetableModel that = (VegetableModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
