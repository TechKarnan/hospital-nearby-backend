package com.hospital.hospital_nearby_backend.repo;

import com.hospital.hospital_nearby_backend.entity.SiteHireUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteHireUserRepository
        extends JpaRepository<SiteHireUser, Long> {

    Optional<SiteHireUser> findByPhone(String phone);
}
