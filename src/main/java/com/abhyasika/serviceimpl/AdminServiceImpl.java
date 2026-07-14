package com.abhyasika.serviceimpl;

import org.springframework.stereotype.Service;

import com.abhyasika.dto.AdminDTO;
import com.abhyasika.dto.AdminLoginDTO;
import com.abhyasika.entity.Admin;
import com.abhyasika.repository.AdminRepository;
import com.abhyasika.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminDTO login(AdminLoginDTO loginDTO) {

        Admin admin = adminRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!admin.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        AdminDTO response = new AdminDTO();

        response.setName(admin.getName());
        response.setEmail(admin.getEmail());
        response.setMobile(admin.getMobile());

        return response;
    }
}