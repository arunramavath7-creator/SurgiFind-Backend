package com.anurag.backend.controller;

import com.anurag.backend.entity.Hospital;
import com.anurag.backend.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin(origins = "*")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // Get all hospitals
    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    // Search hospitals by city
    @GetMapping("/search")
    public List<Hospital> searchHospitals(@RequestParam String city) {
        return hospitalService.searchByCity(city);
    }

    // Get hospital by ID
    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id).orElse(null);
    }

    // Add hospital
    @PostMapping
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    // Update hospital
    @PutMapping("/{id}")
    public Hospital updateHospital(
            @PathVariable Long id,
            @RequestBody Hospital hospital) {

        return hospitalService.updateHospital(id, hospital);
    }

    // Delete hospital
    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
    }
}