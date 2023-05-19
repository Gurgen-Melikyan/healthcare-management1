package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateTime;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

}
