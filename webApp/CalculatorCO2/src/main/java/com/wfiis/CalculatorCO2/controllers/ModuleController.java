package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.module.ModuleFacade;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
import com.wfiis.CalculatorCO2.module.model.ModuleModel;
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
@RequestMapping("/module")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ModuleController {
    private final ModuleFacade moduleFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleModel> createModule(UsernamePasswordAuthenticationToken idToken, @RequestBody ModuleCreateModel moduleCreateModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.createModule(moduleCreateModel, principal.getId()));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleModel> editModule(UsernamePasswordAuthenticationToken idToken, @RequestBody ModuleCreateModel moduleCreateModel, @PathVariable Long id){
        return ResponseEntity.ok(moduleFacade.editModule(moduleCreateModel, id));
    }

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleModel>> getCompanyModules(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getCompanyModules(principal.getId()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleModel> getModule(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModule(principal.getId(), id));
    }

    @GetMapping(value = "/stages/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getModuleModules(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModuleStagesById(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteModule(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        moduleFacade.deleteModule(principal.getId(), id);
        return ResponseEntity.ok("Module with id " + id + " deleted");
    }
}
