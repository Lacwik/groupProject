package com.wfiis.CalculatorCO2.controllers;

import com.wfiis.CalculatorCO2.resource.ResourceFacade;
import com.wfiis.CalculatorCO2.resource.model.ResourceModel;
import com.wfiis.CalculatorCO2.user.model.UserAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ResourceController {
    ResourceFacade resourceFacade;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceModel> getResource(UsernamePasswordAuthenticationToken idToken, @PathVariable Long id){
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(resourceFacade.getResource(principal.getId(), id));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceModel>> getAllResources(UsernamePasswordAuthenticationToken idToken) {
        UserAuthenticationPrincipal principal = (UserAuthenticationPrincipal) idToken.getPrincipal();
        return ResponseEntity.ok(resourceFacade.getAllResources(principal.getId()));
    }
}
