package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.management.entity.Student;
import com.library.management.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();

    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {

        return studentService.addStudent(student);

    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {

        return studentService.getStudent(id);

    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {

        return studentService.updateStudent(id, student);

    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

    }

    @GetMapping("/search")
    public List<Student> searchStudents(
            @RequestParam String keyword) {

        return studentService.searchStudents(keyword);

    }

}