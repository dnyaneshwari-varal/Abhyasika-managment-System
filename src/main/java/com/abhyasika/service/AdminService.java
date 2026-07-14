package com.abhyasika.service;

import com.abhyasika.dto.AdminDTO;
import com.abhyasika.dto.AdminLoginDTO;

public interface AdminService {

    AdminDTO login(AdminLoginDTO loginDTO);

}