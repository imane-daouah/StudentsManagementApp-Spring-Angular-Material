package com.example.studentsappspringbackend.web;

import com.example.studentsappspringbackend.entities.Payment;
import com.example.studentsappspringbackend.entities.PaymentStatus;
import com.example.studentsappspringbackend.entities.PaymentType;
import com.example.studentsappspringbackend.entities.Student;
import com.example.studentsappspringbackend.repository.PaymentRepository;
import com.example.studentsappspringbackend.repository.StudentRepository;
import com.example.studentsappspringbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PaymentRestController {
    @Autowired
    private PaymentService paymentService;
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository ;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository){
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }


    @GetMapping("/students/{code}/payments")
    public List<Payment> paymentByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping("/payments/byStatus")
    public List<Payment> paymentByStudent(@RequestParam PaymentStatus status ){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping("/payments/bytype")
    public List<Payment> paymentByType(@RequestParam PaymentType type ){
        return paymentRepository.findByType(type);
    }

    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping("/payment/{id}")
    public Payment getPayment(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping("/studentsByProgramId")
    public List<Student> getStudentsByProgramId(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id ){
        return paymentService.updatePaymentStatus(status,id);
    }
    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file , LocalDate date, double amount,
                              PaymentType type, String studentCode) throws IOException {
        return paymentService.savePayment(file,date,amount,type,studentCode);

    }
    @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException{
        return  paymentService.getPaymentFile(paymentId);

    }






}
