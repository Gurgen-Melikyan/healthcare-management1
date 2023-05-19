package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public String doctorsPage(ModelMap modelMap) {
        List<Patient> all = patientRepository.findAll();
        modelMap.addAttribute("patients", all);
        return "patients";
    }

    @GetMapping("/patients/add")
    public String addPatientPage() {
        return "addPatient";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("patients/remove")
    public String removePatient(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
