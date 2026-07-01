package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.management.dto.LoginRequest;
import com.library.management.dto.LoginResponse;
import com.library.management.service.AdminService;
import com.library.management.service.StudentService;

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

        boolean success = false;

        if ("student".equalsIgnoreCase(request.getRole())) {

            success = studentService.login(
                    request.getEmail(),
                    request.getPassword());

        } else if ("admin".equalsIgnoreCase(request.getRole())) {

            success = adminService.login(
                    request.getEmail(),
                    request.getPassword());

        }

        if (success) {
            return new LoginResponse(true, "Login Successful");
        }

        return new LoginResponse(false, "Invalid Email or Password");

    }

}