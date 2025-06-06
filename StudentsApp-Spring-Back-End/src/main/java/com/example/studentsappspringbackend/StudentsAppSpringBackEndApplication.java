package com.example.studentsappspringbackend;

import com.example.studentsappspringbackend.entities.Payment;
import com.example.studentsappspringbackend.entities.PaymentStatus;
import com.example.studentsappspringbackend.entities.PaymentType;
import com.example.studentsappspringbackend.entities.Student;
import com.example.studentsappspringbackend.repository.PaymentRepository;
import com.example.studentsappspringbackend.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StudentsAppSpringBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsAppSpringBackEndApplication.class, args);
    }
    @Bean
    CommandLineRunner commendLineRunner(StudentRepository studentRepository,
                                        PaymentRepository paymentRepository){
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).code("112233").firstName("Mohammed").programId("GLSID").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).code("112244").firstName("Imane").programId("BDCC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).code("112255").firstName("Manal").programId("SDIA").build());

            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();

            studentRepository.findAll().forEach(st->{
                for(int i =0;i<10;i++){
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount((1000+(int)(Math.random()*20000)))
                            .date(LocalDate.now())
                            .status(PaymentStatus.CREATED)
                            .type(paymentTypes[index])
                            .file(UUID.randomUUID().toString())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });

        };
    }


}
