package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.lineStatistics.LineStatisticsFacade;
import com.wfiis.CalculatorCO2.lineStatistics.model.LineStatisticsCreateModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/line/statistics")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class LineStatisticsController {
    private final LineStatisticsFacade lineStatisticsFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineStatisticsCreateModel> createLineStatistics(UsernamePasswordAuthenticationToken idToken, @RequestBody LineStatisticsCreateModel lineCreateModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineStatisticsFacade.createLineStatistics(lineCreateModel, principal.getId()));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineStatisticsCreateModel> editLineStatistics(UsernamePasswordAuthenticationToken idToken, @RequestBody LineStatisticsCreateModel lineCreateModel, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineStatisticsFacade.editLineStatistics(lineCreateModel, id, principal.getId()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineStatisticsCreateModel> getLineStatistics(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineStatisticsFacade.getLineStatistics(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteLineStatistics(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineStatisticsFacade.deleteLineStatistics(principal.getId(), id));
    }
}
