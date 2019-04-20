package com.wfiis.CalculatorCO2.line;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.company.model.CompanyIdentity;
import com.wfiis.CalculatorCO2.line.metadata.LineService;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineFacade {
    private final LineService lineService;
    private final UserMetadataService userMetadataService;

    public LineModel createLine(LineCreateModel lineCreateModel, Long userId) {
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.addLine(CompanyIdentity.of(company.getId()), lineCreateModel, company);
    }

    public LineModel editLine(LineCreateModel lineCreateModel, Long lineId){
        return lineService.editLine(
                CompanyIdentity.of(
                        lineService.getCompanyByLineId(lineId).getId()
                ),
                lineCreateModel,
                lineId
        );
    }

    public List<LineModel> getCompanyLines(Long userId){
        Company company = userMetadataService.getCurrentCompanyWorkingFor(userId);
        return lineService.getCompanyLines(CompanyIdentity.of(company.getId()), company);
    }

    public LineModel getLine(Long userId, Long lineId){
        return lineService.getLineModelById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                lineId
        );
    }

    public void deleteLine(Long userId, Long lineId){
        lineService.deleteLineById(
                CompanyIdentity.of(
                        userMetadataService.getCurrentCompanyWorkingFor(userId).getId()
                ),
                lineId
        );
    }
}
