package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.stage.StageFacade;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stage")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class StageController {
    private StageFacade stageFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StageModel> createStage(UsernamePasswordAuthenticationToken idToken, @RequestBody StageCreateModel stageCreateModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        StageModel stageModel = stageFacade.createStage(stageCreateModel, principal.getId());
        return ResponseEntity.ok(stageModel);
    }
}
