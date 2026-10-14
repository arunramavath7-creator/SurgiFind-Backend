package com.anurag.backend.service;

import com.anurag.backend.entity.Appointment;
import com.anurag.backend.entity.Doctor;
import com.anurag.backend.entity.Surgery;
import com.anurag.backend.repository.AppointmentRepository;
import com.anurag.backend.repository.DoctorRepository;
import com.anurag.backend.repository.SurgeryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final SurgeryRepository surgeryRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            DoctorRepository doctorRepository,
            SurgeryRepository surgeryRepository) {

        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.surgeryRepository = surgeryRepository;
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Create appointment
    public Appointment addAppointment(
            Long doctorId,
            Long surgeryId,
            Appointment appointment) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Surgery surgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new RuntimeException("Surgery not found"));

        appointment.setDoctor(doctor);
        appointment.setSurgery(surgery);

        if (appointment.getStatus() == null ||
                appointment.getStatus().isBlank()) {

            appointment.setStatus("Pending");
        }

        return appointmentRepository.save(appointment);
    }

    // Update appointment
    public Appointment updateAppointment(
            Long id,
            Appointment appointmentDetails) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setPatientName(appointmentDetails.getPatientName());
        appointment.setPatientPhone(appointmentDetails.getPatientPhone());
        appointment.setAppointmentDate(
                appointmentDetails.getAppointmentDate());
        appointment.setAppointmentTime(
                appointmentDetails.getAppointmentTime());

        if (appointmentDetails.getStatus() != null) {
            appointment.setStatus(appointmentDetails.getStatus());
        }

        if (appointmentDetails.getDoctor() != null &&
                appointmentDetails.getDoctor().getId() != null) {

            Long doctorId = appointmentDetails.getDoctor().getId();

            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            appointment.setDoctor(doctor);
        }

        if (appointmentDetails.getSurgery() != null &&
                appointmentDetails.getSurgery().getId() != null) {

            Long surgeryId = appointmentDetails.getSurgery().getId();

            Surgery surgery = surgeryRepository.findById(surgeryId)
                    .orElseThrow(() -> new RuntimeException("Surgery not found"));

            appointment.setSurgery(surgery);
        }

        return appointmentRepository.save(appointment);
    }

    // Delete appointment
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Get appointments by patient phone
    public List<Appointment> getAppointmentsByPatientPhone(
            String patientPhone) {

        return appointmentRepository.findByPatientPhone(patientPhone);
    }

    // Get appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Get appointments by surgery
    public List<Appointment> getAppointmentsBySurgery(Long surgeryId) {
        return appointmentRepository.findBySurgeryId(surgeryId);
    }

    // Get appointments by status
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatusIgnoreCase(status);
    }
}