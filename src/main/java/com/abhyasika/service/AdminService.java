package com.abhyasika.service;

import com.abhyasika.dto.AdminDTO;

public interface AdminService {

    AdminDTO login(String email, String password);

}