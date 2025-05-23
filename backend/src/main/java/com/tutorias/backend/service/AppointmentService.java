package com.tutorias.backend.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.tutorias.backend.model.Appointment;
import com.tutorias.repository.AppointmentRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository repo;
    public AppointmentService(AppointmentRepository repo){ this.repo = repo; }

    public Appointment create(Appointment a){ return repo.save(a); }
    public List<Appointment> forTeacher(String teacher){ return repo.findByTeacher(teacher); }
    public List<Appointment> forStudent(String student){ return repo.findByStudent(student); }
}