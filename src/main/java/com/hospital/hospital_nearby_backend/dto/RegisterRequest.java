package com.hospital.hospital_nearby_backend.dto;

import com.hospital.hospital_nearby_backend.enums.Role;
import com.hospital.hospital_nearby_backend.enums.Skill;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String phone;
    private String mpin;
    private Role role;
    private Skill skill;
    private Integer experienceYears;
    private Double latitude;
    private Double longitude;
}
