package com.hospital.hospital_nearby_backend.repo;

import com.hospital.hospital_nearby_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order ,Long> {
}
