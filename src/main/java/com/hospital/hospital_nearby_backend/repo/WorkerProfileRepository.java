package com.hospital.hospital_nearby_backend.repo;

import com.hospital.hospital_nearby_backend.entity.WorkerProfile;
import com.hospital.hospital_nearby_backend.enums.Role;
import com.hospital.hospital_nearby_backend.enums.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerProfileRepository
        extends JpaRepository<WorkerProfile, Long> {

    List<WorkerProfile> findByAvailableTrue();

    @Query("SELECT wp FROM WorkerProfile wp " +
            "JOIN SiteHireUser u ON u.id = wp.userId " +
            "WHERE wp.available = true " +
            "AND u.role = :role " +
            "AND (:skill IS NULL OR wp.skill = :skill) " +
            "AND wp.latitude BETWEEN :latMin AND :latMax " +
            "AND wp.longitude BETWEEN :lonMin AND :lonMax")
    List<WorkerProfile> findNearbyProfiles(
            @Param("role") Role role,
            @Param("skill") Skill skill,
            @Param("latMin") Double latMin,
            @Param("latMax") Double latMax,
            @Param("lonMin") Double lonMin,
            @Param("lonMax") Double lonMax
    );
}
