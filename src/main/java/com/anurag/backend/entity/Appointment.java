package com.anurag.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String patientPhone;
    private String appointmentDate;
    private String appointmentTime;
    private String status;

    // Relationship with Surgery
    @ManyToOne
    @JoinColumn(name = "surgery_id")
    private Surgery surgery;

    // Relationship with Doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(String patientName,
                       String patientPhone,
                       String appointmentDate,
                       String appointmentTime,
                       String status) {

        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Surgery getter
    public Surgery getSurgery() {
        return surgery;
    }

    // Surgery setter
    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

    // Doctor getter
    public Doctor getDoctor() {
        return doctor;
    }

    // Doctor setter
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
