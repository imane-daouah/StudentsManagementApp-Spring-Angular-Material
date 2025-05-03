package com.example.studentsappspringbackend.repository;

import com.example.studentsappspringbackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByCode(String code);
}
