package com.anurag.backend.controller;

import com.anurag.backend.entity.Surgery;
import com.anurag.backend.service.SurgeryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surgeries")
@CrossOrigin(origins = "*")
public class SurgeryController {

    private final SurgeryService surgeryService;

    public SurgeryController(SurgeryService surgeryService) {
        this.surgeryService = surgeryService;
    }

    // Get all surgeries
    @GetMapping
    public List<Surgery> getAllSurgeries() {
        return surgeryService.getAllSurgeries();
    }

    // Get surgery by ID
    @GetMapping("/{id}")
    public Surgery getSurgeryById(@PathVariable Long id) {
        return surgeryService.getSurgeryById(id).orElse(null);
    }

    // Add surgery
    @PostMapping
    public Surgery addSurgery(@RequestBody Surgery surgery) {
        return surgeryService.addSurgery(surgery);
    }

    // Add surgery to a specific hospital
    @PostMapping("/hospital/{hospitalId}")
    public Surgery addSurgeryToHospital(
            @PathVariable Long hospitalId,
            @RequestBody Surgery surgery) {

        return surgeryService.addSurgeryToHospital(hospitalId, surgery);
    }

    // Update surgery
    @PutMapping("/{id}")
    public Surgery updateSurgery(
            @PathVariable Long id,
            @RequestBody Surgery surgery) {

        return surgeryService.updateSurgery(id, surgery);
    }

    // Assign an existing surgery to a hospital
    @PutMapping("/{surgeryId}/hospital/{hospitalId}")
    public Surgery assignSurgeryToHospital(
            @PathVariable Long surgeryId,
            @PathVariable Long hospitalId) {

        return surgeryService.assignSurgeryToHospital(
                surgeryId,
                hospitalId
        );
    }

    // Delete surgery
    @DeleteMapping("/{id}")
    public void deleteSurgery(@PathVariable Long id) {
        surgeryService.deleteSurgery(id);
    }

    // Search surgeries by city
    @GetMapping("/search/city")
    public List<Surgery> searchByCity(@RequestParam String city) {
        return surgeryService.searchByCity(city);
    }

    // Search surgeries by surgery type
    @GetMapping("/search/type")
    public List<Surgery> searchBySurgeryType(
            @RequestParam String surgeryType) {

        return surgeryService.searchBySurgeryType(surgeryType);
    }
}