package com.abhyasika.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhyasika.dto.AdminDTO;
import com.abhyasika.dto.AdminLoginDTO;
import com.abhyasika.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminDTO> login(@Valid @RequestBody AdminLoginDTO loginDTO) {

        AdminDTO admin = adminService.login(loginDTO);

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}