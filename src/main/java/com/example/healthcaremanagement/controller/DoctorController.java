package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Value("${healthcareManagement.upload.image.path}")
    private String imageUploadPath;

    @GetMapping("/doctors")
    public String doctorsPage(ModelMap modelMap) {
        List<Doctor> all = doctorRepository.findAll();
        modelMap.addAttribute("doctors", all);
        return "doctors";
    }

    @GetMapping("/doctors/add")
    public String doctorAddPage() {
        return "addDoctor";
    }

    @PostMapping("/doctors/add")
    public String addDoctor(@ModelAttribute Doctor doctor, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/remove")
    private String removeDoctor(@RequestParam("id") int id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }
}
