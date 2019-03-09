package com.b.calculator.controller;

import com.b.calculator.model.Line;
import com.b.calculator.repository.CompanyRepository;
import com.b.calculator.repository.LineRepository;
import com.b.calculator.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LineController {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/selectCompany")
    public String selectCompany(Model model) {
        //if (Rozpoznanie typu konta == (User || Expert)){
        //  return "/Line/selectCompany"
        //}
        //long id = Company Id
        //return "redirect:/Line/companyLines/"+id;
    }

    @GetMapping("/companyLines/{id}")
    public String companyLines(Model model, @PathVariable Long id) {
        model.addAttribute("lines", lineRepository.findLinesByCompany(companyRepository.findById(id).get()));
        return "/Line/lines";
    }

    @RequestMapping(value = "/lineSave", method = RequestMethod.POST)
    public String lineSave(
            @ModelAttribute("line") Line line,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "error";
        }
        lineRepository.save(line);

        return "redirect:/lines";
    }

    @GetMapping("/line/edit/{id}")
    public String lineEdit(Model model, @PathVariable Long id)
    {
        Line line = lineRepository.findById(id).get();
        model.addAttribute("line", line);
        model.addAttribute("availableStages", stageRepository.findStagesByCompany(line.getCompany()));
        model.addAttribute("outsourcedStages", stageRepository.findStagesByOutsourced(1l));
        return "/Line/editLine";
    }

    @GetMapping("/line/delete/{id}")
    public String lineDelete(Model model, @PathVariable Long id)
    {
        model.addAttribute("line", lineRepository.findById(id).get());
        return "/Line/deleteLine";
    }


    @GetMapping("/line/delete/confirmed/{id}")
    public String lineDeleteConfirmed(Model model, @PathVariable Long id)
    {
        lineRepository.deleteById(id);
        return "redirect:/lines";
    }

    @GetMapping("/line/create")
    public String lineCreate(Model model)
    {
        model.addAttribute("line", new Line());
        return "/Line/createLine";
    }
}
