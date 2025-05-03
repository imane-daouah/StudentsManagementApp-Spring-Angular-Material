package com.example.studentsappspringbackend.service;

import com.example.studentsappspringbackend.entities.Payment;
import com.example.studentsappspringbackend.entities.PaymentStatus;
import com.example.studentsappspringbackend.entities.PaymentType;
import com.example.studentsappspringbackend.entities.Student;
import com.example.studentsappspringbackend.repository.PaymentRepository;
import com.example.studentsappspringbackend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository ;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository){
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }
    public Payment savePayment(MultipartFile file , LocalDate date, double amount,
                               PaymentType type, String studentCode) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"students-app-files","payemnts");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileId = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"students-app-files","payemnts",fileId+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment= Payment.builder()
                .type(type)
                .amount(amount)
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .build();
        paymentRepository.save(payment);
        Payment savedPayments = paymentRepository.save(payment);
        return savedPayments ;


    }

}
