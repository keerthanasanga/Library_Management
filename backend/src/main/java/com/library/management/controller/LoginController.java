package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.management.dto.LoginRequest;
import com.library.management.dto.LoginResponse;
import com.library.management.entity.Student;
import com.library.management.service.AdminService;
import com.library.management.service.StudentService;

import com.library.management.entity.Admin;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @PostMapping
public LoginResponse login(@RequestBody LoginRequest request) {

    if ("student".equalsIgnoreCase(request.getRole())) {

        boolean success = studentService.login(
                request.getEmail(),
                request.getPassword());

        if (success) {

            Student student =
                    studentService.getStudentByEmail(request.getEmail());

            return new LoginResponse(
                    true,
                    "Login Successful",
                    student.getStudentId(),
                    student.getName(),
                    "student"
            );
        }

    } else if ("admin".equalsIgnoreCase(request.getRole())) {

        boolean success = adminService.login(
                request.getEmail(),
                request.getPassword());

        if (success) {

            Admin admin =
                    adminService.getAdminByEmail(request.getEmail());

            return new LoginResponse(
                    true,
                    "Login Successful",
                    admin.getAdminId(),
                    admin.getName(),
                    "admin"
            );
        }

    }

    return new LoginResponse(false, "Invalid Email or Password");

}
}