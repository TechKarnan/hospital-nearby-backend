package com.hospital.hospital_nearby_backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String phone;
    private String mpin;
}