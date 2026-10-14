package com.anurag.backend.service;

import com.anurag.backend.entity.Hospital;
import com.anurag.backend.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    // Get all hospitals
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // Get hospital by ID
    public Optional<Hospital> getHospitalById(Long id) {
        return hospitalRepository.findById(id);
    }

    // Add a new hospital
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // Update hospital
    public Hospital updateHospital(Long id, Hospital hospitalDetails) {

        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        hospital.setName(hospitalDetails.getName());
        hospital.setLocation(hospitalDetails.getLocation());
        hospital.setCity(hospitalDetails.getCity());
        hospital.setState(hospitalDetails.getState());
        hospital.setContactNumber(hospitalDetails.getContactNumber());

        return hospitalRepository.save(hospital);
    }

    // Delete hospital
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    // Search hospitals by city
    public List<Hospital> searchByCity(String city) {
        return hospitalRepository.findByCityIgnoreCase(city);
    }
}