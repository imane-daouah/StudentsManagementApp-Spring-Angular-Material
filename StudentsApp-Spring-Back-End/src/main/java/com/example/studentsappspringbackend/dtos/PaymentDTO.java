package com.example.studentsappspringbackend.dtos;

import com.example.studentsappspringbackend.entities.PaymentStatus;
import com.example.studentsappspringbackend.entities.PaymentType;
import com.example.studentsappspringbackend.entities.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentDTO {

    private long id ;
    private LocalDate date ;
    private double amount ;
    private PaymentType type ;
    private PaymentStatus status ;
}
