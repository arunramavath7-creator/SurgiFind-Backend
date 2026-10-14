package com.anurag.backend.service;

import com.anurag.backend.entity.Hospital;
import com.anurag.backend.entity.Surgery;
import com.anurag.backend.repository.HospitalRepository;
import com.anurag.backend.repository.SurgeryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurgeryService {

    private final SurgeryRepository surgeryRepository;
    private final HospitalRepository hospitalRepository;

    public SurgeryService(
            SurgeryRepository surgeryRepository,
            HospitalRepository hospitalRepository) {

        this.surgeryRepository = surgeryRepository;
        this.hospitalRepository = hospitalRepository;
    }

    // Get all surgeries
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    // Get surgery by ID
    public Optional<Surgery> getSurgeryById(Long id) {
        return surgeryRepository.findById(id);
    }

    // Add a new surgery
    public Surgery addSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    // Add surgery to a specific hospital
    public Surgery addSurgeryToHospital(Long hospitalId, Surgery surgery) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        surgery.setHospital(hospital);

        // Automatically take hospital details
        surgery.setHospitalName(hospital.getName());
        surgery.setCity(hospital.getCity());
        surgery.setState(hospital.getState());

        return surgeryRepository.save(surgery);
    }

    // Update surgery
    public Surgery updateSurgery(Long id, Surgery surgeryDetails) {

        Surgery surgery = surgeryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surgery not found"));

        surgery.setSurgeryName(surgeryDetails.getSurgeryName());
        surgery.setSurgeryType(surgeryDetails.getSurgeryType());
        surgery.setHospitalName(surgeryDetails.getHospitalName());
        surgery.setCity(surgeryDetails.getCity());
        surgery.setState(surgeryDetails.getState());
        surgery.setSurgeryDate(surgeryDetails.getSurgeryDate());
        surgery.setEstimatedCost(surgeryDetails.getEstimatedCost());
        surgery.setAvailability(surgeryDetails.getAvailability());

        // If hospital information is provided, update the relationship
        if (surgeryDetails.getHospital() != null) {

            Long hospitalId = surgeryDetails.getHospital().getId();

            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));

            surgery.setHospital(hospital);

            // Keep hospital details synchronized
            surgery.setHospitalName(hospital.getName());
            surgery.setCity(hospital.getCity());
            surgery.setState(hospital.getState());
        }

        return surgeryRepository.save(surgery);
    }

    // Assign an existing surgery to a hospital
    public Surgery assignSurgeryToHospital(Long surgeryId, Long hospitalId) {

        Surgery surgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new RuntimeException("Surgery not found"));

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        surgery.setHospital(hospital);

        // Synchronize hospital information
        surgery.setHospitalName(hospital.getName());
        surgery.setCity(hospital.getCity());
        surgery.setState(hospital.getState());

        return surgeryRepository.save(surgery);
    }

    // Delete surgery
    public void deleteSurgery(Long id) {
        surgeryRepository.deleteById(id);
    }

    // Search surgeries by city
    public List<Surgery> searchByCity(String city) {
        return surgeryRepository.findByCityIgnoreCase(city);
    }

    // Search surgeries by surgery type
    public List<Surgery> searchBySurgeryType(String surgeryType) {
        return surgeryRepository.findBySurgeryTypeIgnoreCase(surgeryType);
    }
}