package com.anurag.backend.repository;

import com.anurag.backend.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientPhone(String patientPhone);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findBySurgeryId(Long surgeryId);

    List<Appointment> findByStatusIgnoreCase(String status);
}