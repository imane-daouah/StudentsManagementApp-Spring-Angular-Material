package com.example.studentsappspringbackend.repository;

import com.example.studentsappspringbackend.entities.Payment;
import com.example.studentsappspringbackend.entities.PaymentStatus;
import com.example.studentsappspringbackend.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);

    List<Payment> findByType(PaymentType paymentType);
}
