package com.hospital.hospital_nearby_backend.repo;

import com.hospital.hospital_nearby_backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem,Long> {
}
