package com.anurag.backend.controller;

import com.anurag.backend.entity.Appointment;
import com.anurag.backend.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id).orElse(null);
    }

    // Create appointment for a doctor and surgery
    @PostMapping("/doctor/{doctorId}/surgery/{surgeryId}")
    public Appointment addAppointment(
            @PathVariable Long doctorId,
            @PathVariable Long surgeryId,
            @RequestBody Appointment appointment) {

        return appointmentService.addAppointment(
                doctorId,
                surgeryId,
                appointment
        );
    }

    // Update appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        return appointmentService.updateAppointment(
                id,
                appointment
        );
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    // Get appointments by patient phone
    @GetMapping("/patient/{patientPhone}")
    public List<Appointment> getAppointmentsByPatientPhone(
            @PathVariable String patientPhone) {

        return appointmentService
                .getAppointmentsByPatientPhone(patientPhone);
    }

    // Get appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {

        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    // Get appointments by surgery
    @GetMapping("/surgery/{surgeryId}")
    public List<Appointment> getAppointmentsBySurgery(
            @PathVariable Long surgeryId) {

        return appointmentService.getAppointmentsBySurgery(surgeryId);
    }

    // Get appointments by status
    @GetMapping("/status/{status}")
    public List<Appointment> getAppointmentsByStatus(
            @PathVariable String status) {

        return appointmentService.getAppointmentsByStatus(status);
    }
}