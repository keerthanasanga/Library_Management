package com.library.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entity.Admin;
import com.library.management.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean login(String email, String password) {

        Optional<Admin> admin = adminRepository.findByEmail(email);

        if (admin.isPresent()) {
            return admin.get().getPassword().equals(password);
        }

        return false;
    }
    public Admin getAdminByEmail(String email) {

    return adminRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Admin not found"));

}

}