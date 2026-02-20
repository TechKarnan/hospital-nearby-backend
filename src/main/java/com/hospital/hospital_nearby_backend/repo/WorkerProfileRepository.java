package com.hospital.hospital_nearby_backend.repo;

import com.hospital.hospital_nearby_backend.entity.WorkerProfile;
import com.hospital.hospital_nearby_backend.enums.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerProfileRepository
        extends JpaRepository<WorkerProfile, Long> {

    List<WorkerProfile> findByAvailableTrue();
}
