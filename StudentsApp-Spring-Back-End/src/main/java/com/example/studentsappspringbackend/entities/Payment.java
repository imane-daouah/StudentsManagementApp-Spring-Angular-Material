package com.example.studentsappspringbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private LocalDate date ;
    private double amount ;
    private PaymentType type ;
    private  PaymentStatus status = PaymentStatus.CREATED ;
    private String file;
    @ManyToOne
    private  Student student ;

}
