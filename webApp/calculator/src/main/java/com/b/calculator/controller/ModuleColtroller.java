package com.b.calculator.controller;

import com.b.calculator.model.Company;
import com.b.calculator.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModuleColtroller {
    @Autowired
    ModuleRepository moduleRepository;

    @GetMapping("/modules")
    public String modules(Model model)
    {
        Company company = new Company();
        //model.addAttribute("modules", moduleRepository.findModulesByCompany(company));
        return "/Line/lines";
    }
}
