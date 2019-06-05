package com.wfiis.CalculatorCO2.lineStatistics.model;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.stageResourceValue.model.StageResourceValueCreateModel;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LineStatisticsCreateModel {
    private Long id;
    private Line line;
    private List<StageResourceValueCreateModel> stageResourceValueCM;
    private Vegetable vegetable;
    private Float carbonPrint;
    private Company company;
}
