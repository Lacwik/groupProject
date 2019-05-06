package com.wfiis.CalculatorCO2.resourceFlags.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceFlagsCreateModel {
    private Boolean waterFlag;
    private Boolean dieselFlag;
    private Boolean lpgFlag;
    private Boolean foilFlag;
}