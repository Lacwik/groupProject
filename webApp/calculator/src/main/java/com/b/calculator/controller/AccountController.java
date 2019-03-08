package com.b.calculator.controller;

import com.b.calculator.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpertRepository expertRepository;

    @GetMapping("/Account/mainPage")
    public String mainPage() {
        return "redirect:/Account/mainPage";
    }
}