package com.ELEC5620.doctorService.controller;

import com.ELEC5620.doctorService.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.ELEC5620.doctorService.model.QuestionRequest;
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctor")
    public String getHealthAdvice(@RequestBody QuestionRequest request) {
        return doctorService.ask(request.getQuestion());
    }
}
