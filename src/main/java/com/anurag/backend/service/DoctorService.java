package com.anurag.backend.service;

import com.anurag.backend.entity.Doctor;
import com.anurag.backend.entity.Hospital;
import com.anurag.backend.repository.DoctorRepository;
import com.anurag.backend.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    public DoctorService(
            DoctorRepository doctorRepository,
            HospitalRepository hospitalRepository) {

        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Add doctor to a specific hospital
    public Doctor addDoctorToHospital(Long hospitalId, Doctor doctor) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        doctor.setHospital(hospital);

        return doctorRepository.save(doctor);
    }

    // Update doctor
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setDoctorName(doctorDetails.getDoctorName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setQualification(doctorDetails.getQualification());
        doctor.setExperience(doctorDetails.getExperience());
        doctor.setContactNumber(doctorDetails.getContactNumber());
        doctor.setAvailability(doctorDetails.getAvailability());

        if (doctorDetails.getHospital() != null) {

            Long hospitalId = doctorDetails.getHospital().getId();

            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));

            doctor.setHospital(hospital);
        }

        return doctorRepository.save(doctor);
    }

    // Assign existing doctor to a hospital
    public Doctor assignDoctorToHospital(Long doctorId, Long hospitalId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        doctor.setHospital(hospital);

        return doctorRepository.save(doctor);
    }

    // Delete doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Search doctors by specialization
    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization);
    }

    // Get all doctors of a hospital
    public List<Doctor> getDoctorsByHospital(Long hospitalId) {
        return doctorRepository.findByHospitalId(hospitalId);
    }
}