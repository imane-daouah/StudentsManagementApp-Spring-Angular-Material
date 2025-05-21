package com.example.studentsappspringbackend.web;

import com.example.studentsappspringbackend.entities.Payment;
import com.example.studentsappspringbackend.entities.PaymentStatus;
import com.example.studentsappspringbackend.entities.PaymentType;
import com.example.studentsappspringbackend.entities.Student;
import com.example.studentsappspringbackend.repository.PaymentRepository;
import com.example.studentsappspringbackend.repository.StudentRepository;
import com.example.studentsappspringbackend.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
public class StudentRestController {

}
