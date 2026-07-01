package com.library.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entity.Student;
import com.library.management.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean login(String email, String password) {

        Optional<Student> student = studentRepository.findByEmail(email);

        if (student.isPresent()) {
            return student.get().getPassword().equals(password);
        }

        return false;
    }

}