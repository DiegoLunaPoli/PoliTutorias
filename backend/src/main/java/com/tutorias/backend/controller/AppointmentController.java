package com.tutorias.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorias.backend.model.Appointment;
import com.tutorias.backend.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("http://localhost:3000")
public class AppointmentController {
    private final AppointmentService service;
    public AppointmentController(AppointmentService s){ this.service = s; }

    @PostMapping("/teacher")
    public Appointment create(@RequestBody Appointment a){
        return service.create(a);
    }

    @GetMapping("/teacher/{username}")
    public List<Appointment> byTeacher(@PathVariable String username){
        return service.forTeacher(username);
    }

    @GetMapping("/student/{username}")
    public List<Appointment> byStudent(@PathVariable String username){
        return service.forStudent(username);
    }
}