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

    //Account
    @GetMapping("/home")
    public String mainPage() {
        return "/Account/mainPage";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "/Account/forgotPassword";
    }

    //Account/Company
    @GetMapping("/loginCompany")
    public String loginCompany() {
        return "/Account/Company/loginCompany";
    }

    @GetMapping("/registerCompany")
    public String registerCompany() {
        return "/Account/Company/registerCompany";
    }

    //Account/Expert
    @GetMapping("/loginExpert")
    public String loginExpert() {
        return "/Account/Expert/loginExpert";
    }

    @GetMapping("/registerExpert")
    public String registerExpert() {
        return "/Account/Expert/registerExpert";
    }

    //Account/User
    @GetMapping("/loginUser")
    public String loginUser() {
        return "/Account/User/loginUser";
    }

    @GetMapping("/registerUser")
    public String registerUser() {
        return "/Account/User/registerUser";
    }
}