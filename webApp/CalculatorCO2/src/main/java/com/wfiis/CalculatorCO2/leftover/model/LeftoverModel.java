package com.wfiis.CalculatorCO2.leftover.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class LeftoverModel extends LeftoverCreateModel {
    private Long id;

    public LeftoverModel(String name, long id){
        super(name);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeftoverModel that = (LeftoverModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
