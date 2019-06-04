package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.leftover.model.LeftoverModel;
import com.wfiis.CalculatorCO2.module.ModuleFacade;
import com.wfiis.CalculatorCO2.module.model.ModuleCreateModel;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleModel> getModule(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModule(principal.getId(), id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteModule(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.deleteModule(principal.getId(), id));
    }

    @GetMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleModel>> getCompanyModules(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getCompanyModules(principal.getId()));
    }

    @GetMapping(value = "/stages/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StageModel>> getModuleStages(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModuleStages(principal.getId(), id));
    }

    @GetMapping(value = "/vegetables/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VegetableModel>> getModuleVegetables(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModuleVegetables(principal.getId(), id));
    }

    @GetMapping(value = "/resources/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceModel>> getModuleResources(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModuleResources(principal.getId(), id));
    }

    @GetMapping(value = "/leftovers/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeftoverModel>> getModuleLeftovers(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModuleLeftovers(principal.getId(), id));
    }

    @GetMapping(value = "/default", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleModel>> getDefaultModules(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getDefaultModules(principal.getId()));
    }

    @PostMapping(value = "/vegetables/modules", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleModel>> getModulesByVegetableList(UsernamePasswordAuthenticationToken idToken, @RequestBody List<VegetableModel> vegetableModel) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(moduleFacade.getModulesByVegetableList(vegetableModel, principal.getId()));
    }
}
