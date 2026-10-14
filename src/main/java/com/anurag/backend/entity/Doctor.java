package com.anurag.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;
    private String specialization;
    private String qualification;
    private String experience;
    private String contactNumber;
    private String availability;

    // Relationship with Hospital
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public Doctor() {
    }

    public Doctor(String doctorName,
                  String specialization,
                  String qualification,
                  String experience,
                  String contactNumber,
                  String availability) {

        this.doctorName = doctorName;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experience = experience;
        this.contactNumber = contactNumber;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    // Hospital getter
    public Hospital getHospital() {
        return hospital;
    }

    // Hospital setter
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}