package com.wfiis.CalculatorCO2.stageResourceValue.model;

import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StageResourceValueCreateModel {
    private Resource resource;
    private float value;
    private Stage stage;
    private Long time;
}
