package com.wfiis.CalculatorCO2.calculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class GusResource {
    private Long id;
    private String name_pl;
    private String gus_id;
    private Long unit_id;
    private String shortcut_unit;
    private Float equiv_kgCo2;
}
