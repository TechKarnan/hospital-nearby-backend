package com.hospital.hospital_nearby_backend.service;

import com.hospital.hospital_nearby_backend.entity.SiteHireUser;
import com.hospital.hospital_nearby_backend.entity.WorkerProfile;
import com.hospital.hospital_nearby_backend.enums.Role;
import com.hospital.hospital_nearby_backend.repo.SiteHireUserRepository;
import com.hospital.hospital_nearby_backend.repo.WorkerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final WorkerProfileRepository workerRepo;
    private final SiteHireUserRepository userRepo;

    private static final double RADIUS_KM = 5.0;

    public List<Map<String, Object>> findNearbyUsers(
            Double lat,
            Double lon,
            Role targetRole) {

        List<WorkerProfile> profiles = workerRepo.findByAvailableTrue();

        return profiles.stream()
                .map(profile -> {

                    if (profile.getLatitude() == null ||
                            profile.getLongitude() == null)
                        return null;

                    SiteHireUser user =
                            userRepo.findById(profile.getUserId())
                                    .orElse(null);

                    if (user == null || user.getRole() != targetRole)
                        return null;

                    double distance = calculateDistance(
                            lat, lon,
                            profile.getLatitude(),
                            profile.getLongitude());

                    if (distance > RADIUS_KM)
                        return null;

                    Map<String, Object> response = new HashMap<>();
                    response.put("userId", user.getId());
                    response.put("name", user.getName());
                    response.put("phone", user.getPhone());
                    response.put("skill", profile.getSkill());
                    response.put("experienceYears",
                            profile.getExperienceYears());
                    response.put("distanceKm", distance);

                    return response;

                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(m ->
                        (Double) m.get("distanceKm")))
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1,
                                     double lat2, double lon2) {

        double R = 6371; // Earth radius in KM

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2)
                * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}