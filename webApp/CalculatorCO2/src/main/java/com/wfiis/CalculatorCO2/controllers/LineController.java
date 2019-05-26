package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.line.LineFacade;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
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
@RequestMapping("/line")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class LineController {
    private final LineFacade lineFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineModel> createLine(UsernamePasswordAuthenticationToken idToken, @RequestBody LineCreateModel lineCreateModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.createLine(lineCreateModel, principal.getId()));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineModel> editLine(UsernamePasswordAuthenticationToken idToken, @RequestBody LineCreateModel lineCreateModel, @PathVariable Long id){
        return ResponseEntity.ok(lineFacade.editLine(lineCreateModel, id));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineModel> getLine(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLine(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteLine(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.deleteLine(principal.getId(), id));
    }

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LineModel>> getCompanyLines(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getCompanyLines(principal.getId()));
    }

    @GetMapping(value = "/stages/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getLineStages(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLineStages(principal.getId(), id));
    }

    @GetMapping(value = "/modules/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleModel>> getLineModules(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLineModules(principal.getId(), id));
    }

    @GetMapping(value = "/vegetables/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VegetableModel>> getLineVegetables(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLineVegetables(principal.getId(), id));
    }

    @GetMapping(value = "/resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceModel>> getLineResources(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLineResources(principal.getId(), id));
    }

    @GetMapping(value = "/leftovers/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeftoverModel>> getLineLeftovers(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLineLeftovers(principal.getId(), id));
    }
}
