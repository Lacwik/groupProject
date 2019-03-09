package com.b.calculator.controller;

import com.b.calculator.repository.LineRepository;
import com.b.calculator.repository.ModuleRepository;
import com.b.calculator.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StageController {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    ModuleRepository moduleRepository;
}
