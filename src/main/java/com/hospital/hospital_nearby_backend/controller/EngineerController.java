package com.hospital.hospital_nearby_backend.controller;
import com.hospital.hospital_nearby_backend.entity.SiteHireUser;
import com.hospital.hospital_nearby_backend.entity.WorkerProfile;
import com.hospital.hospital_nearby_backend.enums.Role;
import com.hospital.hospital_nearby_backend.enums.Skill;
import com.hospital.hospital_nearby_backend.repo.SiteHireUserRepository;
import com.hospital.hospital_nearby_backend.repo.WorkerProfileRepository;
import com.hospital.hospital_nearby_backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sitehire/engineer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EngineerController {

    private final WorkerProfileRepository workerRepo;
    private final SiteHireUserRepository userRepo;

    private final LocationService locationService;

    @GetMapping("/nearby")
    public List<Map<String, Object>> getNearbyWorkers(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam Skill skill) {

        return locationService.findNearbyUsers(
                lat, lon, Role.WORKER,skill);
    }
}
