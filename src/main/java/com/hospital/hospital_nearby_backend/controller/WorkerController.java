package com.hospital.hospital_nearby_backend.controller;

import com.hospital.hospital_nearby_backend.entity.WorkerProfile;
import com.hospital.hospital_nearby_backend.enums.Role;
import com.hospital.hospital_nearby_backend.repo.WorkerProfileRepository;
import com.hospital.hospital_nearby_backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sitehire/worker")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WorkerController {

    private final WorkerProfileRepository repository;

    private final LocationService locationService;

    @PostMapping("/profile")
    public WorkerProfile createProfile(@RequestBody WorkerProfile profile) {
        profile.setAvailable(true);
        return repository.save(profile);
    }


    @PutMapping("/location")
    public WorkerProfile updateLocation(@RequestBody WorkerProfile profile) {

        WorkerProfile wp = repository.findById(profile.getUserId())
                .orElseGet(() -> WorkerProfile.builder()
                        .userId(profile.getUserId())
                        .available(true)   // default value
                        .build());

        wp.setLatitude(profile.getLatitude());
        wp.setLongitude(profile.getLongitude());

        return repository.save(wp);
    }

    @PutMapping("/availability/{userId}")
    public WorkerProfile updateAvailability(@PathVariable Long userId,
                                            @RequestParam Boolean available) {

        WorkerProfile wp = repository.findById(userId).orElseThrow();
        wp.setAvailable(available);

        return repository.save(wp);
    }

    @GetMapping("/nearby")
    public List<Map<String, Object>> getNearbyEngineers(
            @RequestParam Double lat,
            @RequestParam Double lon) {

        return locationService.findNearbyUsers(
                lat, lon, Role.ENGINEER);
    }
}