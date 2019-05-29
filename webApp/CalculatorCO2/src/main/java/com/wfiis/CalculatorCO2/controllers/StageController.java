package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.stage.StageFacade;
import com.wfiis.CalculatorCO2.stage.model.StageCreateModel;
import com.wfiis.CalculatorCO2.stage.model.StageModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import com.wfiis.CalculatorCO2.vegetable.model.VegetableModel;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StageModel> getStage(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStage(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStage(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.deleteStage(principal.getId(), id));
    }

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getCompanyStages(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getCompanyStages(principal.getId()));
    }

    @GetMapping(value = "/lines/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LineModel>> getStageLines(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStageLines(principal.getId(), id));
    }

    @GetMapping(value = "/modules/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleModel>> getStageModules(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStageModules(principal.getId(), id));
    }

    @GetMapping(value = "/vegetables/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VegetableModel>> getStageVegetables(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStageVegetables(principal.getId(), id));
    }

    @GetMapping(value = "/resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceModel>> getStageResources(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStageResources(principal.getId(), id));
    }

    @GetMapping(value = "/leftovers/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeftoverModel>> getStageLeftovers(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStageLeftovers(principal.getId(), id));
    }

    @GetMapping(value = "/default", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getDefaultStages(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getDefaultStages(principal.getId()));
    }

    @GetMapping(value = "/vegetables/stages", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getStagesByVegetableList(UsernamePasswordAuthenticationToken idToken, @RequestBody List<VegetableModel> vegetableModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(stageFacade.getStagesByVegetableList(vegetableModel, principal.getId()));
    }
}
