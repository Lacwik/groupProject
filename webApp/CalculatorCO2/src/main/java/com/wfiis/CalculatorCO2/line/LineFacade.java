package com.wfiis.CalculatorCO2.line;

import com.wfiis.CalculatorCO2.line.metadata.LineService;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineFacade {
    private LineService lineService;

    public List<LineCreateModel> getOutsourcedLines() {
        return lineService.getModelsFromEntityList(
                lineService.getOutsourcedLines()
        );
    }

    public List<LineCreateModel> getCompanyLines(Long companyId){
        return lineService.getModelsFromEntityList(
                lineService.getCompanyLines(companyId)
        );
    }
}
