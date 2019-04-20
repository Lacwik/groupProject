package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.stage.StageFacade;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stage")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class StageController {
    private final StageFacade stageFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StageModel> createStage(UsernamePasswordAuthenticationToken idToken, @RequestBody StageCreateModel stageCreateModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.createStage(stageCreateModel, principal.getId()));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StageModel> editStage(UsernamePasswordAuthenticationToken idToken, @RequestBody StageCreateModel stageCreateModel, @PathVariable Long id){
        return ResponseEntity.ok(stageFacade.editStage(stageCreateModel, id));
    }

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getCompanyStages(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getCompanyStages(principal.getId()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StageModel> getStage(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStage(principal.getId(), id));
    }

    @GetMapping(value = "/lines/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LineModel>> getStageLines(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStageLinesById(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStage(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        stageFacade.deleteStage(principal.getId(), id);
        return ResponseEntity.ok("Stage with id " + id + " deleted");
    }
}
