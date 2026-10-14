package com.anurag.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "surgeries")
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surgeryName;
    private String surgeryType;

    private String hospitalName;

    private String city;
    private String state;
    private String surgeryDate;
    private Double estimatedCost;
    private String availability;

    // Relationship with Hospital
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public Surgery() {
    }

    public Surgery(String surgeryName,
                   String surgeryType,
                   String hospitalName,
                   String city,
                   String state,
                   String surgeryDate,
                   Double estimatedCost,
                   String availability) {

        this.surgeryName = surgeryName;
        this.surgeryType = surgeryType;
        this.hospitalName = hospitalName;
        this.city = city;
        this.state = state;
        this.surgeryDate = surgeryDate;
        this.estimatedCost = estimatedCost;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(String surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    // Hospital relationship getter
    public Hospital getHospital() {
        return hospital;
    }

    // Hospital relationship setter
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}