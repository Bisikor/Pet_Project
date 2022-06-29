package com.medical.accountingMedical.controllers;

import com.medical.accountingMedical.models.Medicals;
import com.medical.accountingMedical.services.MedicalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MedicalsController {

    @Autowired
    MedicalsService medicalService;

    @GetMapping(value = "/medical")
    public String findAll(Model model) {
        Iterable<Medicals> medicals = medicalService.gelAll();
        model.addAttribute("medicals", medicals);
        return "/medical-list";
    }

    @GetMapping(value = "/medical-add")
    public String addMedicalsForms(Medicals medicals) {
        return "medical-add";
    }

    @PostMapping(value = "/medical-add")
    public String addMedicals(Medicals medicals) {
        medicalService.save(medicals);
        return "redirect:/medical";
    }

    @GetMapping(value = "medical-delete/{id}")
    public String medicalDelete(@PathVariable("id") Long id) {
        medicalService.deleteById(id);
        return "redirect:/medical";
    }

    @PostMapping(value = "medical-delete/{id}")
    public String medicalUpdate(@PathVariable("id") Long id, Model model) {
        Optional<Medicals> medicals = medicalService.findById(id);
        model.addAttribute("Medicals", medicals);
        return "redirect:/medical";

    }

    @GetMapping(value = "/medical-update/{id}")
    public String medicalUpdateForms(@PathVariable("id") Long id, Model model) {
        Optional<Medicals> medicals = medicalService.findById(id);
        model.addAttribute("Medicals", medicals);
        return "/medical-update";
    }

    @PostMapping(value = "/medical-update")
    public String medicalUpdate(Medicals medicals) {
        medicalService.save(medicals);
        return "redirect:/medical";
    }

    //   <td><a th:href="@{user-update/{id}(id=${user.id})}">Edit</a></td>
    //                <td><a th:href="@{user-delete/{id}(id=${user.id})}">Delete</a></td>
}
