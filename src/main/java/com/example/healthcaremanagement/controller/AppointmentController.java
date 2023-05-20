package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/appointments")
    public String appointmentPage(ModelMap modelMap) {
        List<Appointment> all = appointmentRepository.findAll();
        modelMap.addAttribute("appointments", all);
        return "appointments";
    }

    @GetMapping("/appointments/add")
    private String appointmentAddPage(ModelMap modelMap, ModelMap modelMap1) {
        List<Doctor> all = doctorRepository.findAll();
        List<Patient> all1 = patientRepository.findAll();
        modelMap1.addAttribute("patients", all1);
        modelMap.addAttribute("doctors", all);
        return "addAppointment";
    }

    @PostMapping("/appointments/add")
    public String addAppointment(@ModelAttribute Appointment appointment,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        appointment.setDateTime(new Date());
        appointment.setUser(currentUser.getUser());
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/remove")
    public String removeAppointment(@RequestParam("id") int id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
