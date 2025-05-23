package com.tutorias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorias.backend.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByTeacher(String teacher);
    List<Appointment> findByStudent(String student);
}