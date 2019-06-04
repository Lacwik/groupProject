package com.wfiis.CalculatorCO2.lineStatistics.model;

import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.stageResourceValue.model.StageResourceValueCreateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LineStatisticsCreateModel {
    private Line line;
    private List<StageResourceValueCreateModel> stageResourceValueCM;
}
