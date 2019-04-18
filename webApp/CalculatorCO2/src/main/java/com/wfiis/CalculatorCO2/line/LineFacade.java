package com.wfiis.CalculatorCO2.line;

import com.wfiis.CalculatorCO2.line.metadata.LineService;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineFacade {
    private LineService lineService;

    public List<LineModel> getOutsourcedLines() {
        return lineService.getModelsFromEntityList(
                lineService.getOutsourcedLines()
        );
    }

    public List<LineModel> getCompanyLines(Long companyId){
        return lineService.getModelsFromEntityList(
                lineService.getCompanyLines(companyId)
        );
    }
}
