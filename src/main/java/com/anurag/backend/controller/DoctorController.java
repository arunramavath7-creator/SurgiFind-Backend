package com.anurag.backend.controller;

import com.anurag.backend.entity.Doctor;
import com.anurag.backend.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id).orElse(null);
    }

    // Add doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    // Add doctor to a specific hospital
    @PostMapping("/hospital/{hospitalId}")
    public Doctor addDoctorToHospital(
            @PathVariable Long hospitalId,
            @RequestBody Doctor doctor) {

        return doctorService.addDoctorToHospital(hospitalId, doctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return doctorService.updateDoctor(id, doctor);
    }

    // Assign existing doctor to a hospital
    @PutMapping("/{doctorId}/hospital/{hospitalId}")
    public Doctor assignDoctorToHospital(
            @PathVariable Long doctorId,
            @PathVariable Long hospitalId) {

        return doctorService.assignDoctorToHospital(
                doctorId,
                hospitalId
        );
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    // Search doctors by specialization
    @GetMapping("/search/specialization")
    public List<Doctor> searchBySpecialization(
            @RequestParam String specialization) {

        return doctorService.searchBySpecialization(specialization);
    }

    // Get doctors by hospital
    @GetMapping("/hospital/{hospitalId}")
    public List<Doctor> getDoctorsByHospital(
            @PathVariable Long hospitalId) {

        return doctorService.getDoctorsByHospital(hospitalId);
    }
}