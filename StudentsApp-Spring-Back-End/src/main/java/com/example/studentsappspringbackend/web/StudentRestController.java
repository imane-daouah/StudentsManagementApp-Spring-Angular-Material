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
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class StudentRestController {


    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private PaymentService paymentService ;


    public StudentRestController(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }
    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping("/payments/{id}")
    private Payment findById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable String id){
        return  studentRepository.findById(id).get();
    }

    @GetMapping("/students/{code}/payments")
    public  List<Payment> findByStudentCode(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }


    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment saePayment(@RequestParam MultipartFile file , LocalDate date, double amount,
                              PaymentType type, String studentCode) throws IOException {
        return paymentService.savePayment(file,date,amount,type,studentCode);

    }



    @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException{
        Payment payment = paymentRepository.findById(paymentId).get();
        String filePath = payment.getFile();
        return  Files.readAllBytes(Path.of(URI.create(filePath)));

    }

    @PutMapping("/payments/updateStatus/{paymentId}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,
                                       @PathVariable Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }


}
