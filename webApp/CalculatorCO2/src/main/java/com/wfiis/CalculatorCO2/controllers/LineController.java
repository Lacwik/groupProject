package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.line.LineFacade;
import com.wfiis.CalculatorCO2.line.model.LineCreateModel;
import com.wfiis.CalculatorCO2.line.model.LineModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
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

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LineModel>> getCompanyLines(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getCompanyLines(principal.getId()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LineModel> getLine(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(lineFacade.getLine(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteLine(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        lineFacade.deleteLine(principal.getId(), id);
        return ResponseEntity.ok("Line with id " + id + " deleted");
    }
}
