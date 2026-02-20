package com.hospital.hospital_nearby_backend.controller;

import com.hospital.hospital_nearby_backend.dto.LoginRequest;
import com.hospital.hospital_nearby_backend.dto.RegisterRequest;
import com.hospital.hospital_nearby_backend.entity.SiteHireUser;
import com.hospital.hospital_nearby_backend.entity.WorkerProfile;
import com.hospital.hospital_nearby_backend.repo.SiteHireUserRepository;
import com.hospital.hospital_nearby_backend.repo.WorkerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sitehire/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final SiteHireUserRepository repository;
    private  final WorkerProfileRepository workerProfileRepository;


    @PostMapping("/register")
    public SiteHireUser register(@RequestBody RegisterRequest request) {

        // 1️⃣ Save User
        SiteHireUser user = SiteHireUser.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .mpin(request.getMpin())
                .role(request.getRole())
                .build();

        SiteHireUser savedUser = repository.save(user);

        // 2️⃣ If Worker → Save WorkerProfile
        if (savedUser.getRole().name().equals("WORKER")) {

            WorkerProfile profile = WorkerProfile.builder()
                    .userId(savedUser.getId())
                    .skill(request.getSkill())
                    .experienceYears(request.getExperienceYears())
                    .latitude(request.getLatitude())
                    .longitude(request.getLongitude())
                    .available(true)
                    .build();

            workerProfileRepository.save(profile);
        }

        return savedUser;
    }
    @PostMapping("/login")
    public SiteHireUser login(@RequestBody LoginRequest request) {
        return repository.findByPhone(request.getPhone())
                .filter(u -> u.getMpin().equals(request.getMpin()))
                .orElseThrow(() -> new RuntimeException("Invalid MPIN"));
    }
}
