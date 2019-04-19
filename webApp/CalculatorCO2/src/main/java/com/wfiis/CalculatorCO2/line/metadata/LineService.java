package com.wfiis.CalculatorCO2.line.metadata;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.line.metadata.repository.LineRepository;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.stage.metadata.StageAssembler;
import com.wfiis.CalculatorCO2.stage.metadata.StageService;
import com.wfiis.CalculatorCO2.vegetable.metadata.VegetableService;

import java.util.ArrayList;
import java.util.List;

public class LineService {
    private LineRepository lineRepository;
    private StageService stageService;
    private VegetableService vegetableService;
    private StageAssembler stageAssembler;

    public List<Line> getOutsourcedLines() {
        return lineRepository.findLinesByOutsourced(1);
    }

    public List<Line> getCompanyLines(Long companyId) {
        return lineRepository.findLinesByCompanyId(companyId);
    }


    public List<LineModel> getModelsFromEntityList(List<Line> lines) {
        List<LineModel> outLines = new ArrayList<>();

        for (Line line : lines) {
            outLines.add(getModelFromEntity(line));
        }

        return outLines;
    }

    public LineModel getModelFromEntity(Line line) {
        return new LineModel(
                line.getId(),
                line.getName(),
                line.getOutsourced(),
                stageAssembler.getModelsFromEntityList(line.getStages()),
                vegetableService.getModelsFromEntityList(line.getVegetables())
        );
    }
}
