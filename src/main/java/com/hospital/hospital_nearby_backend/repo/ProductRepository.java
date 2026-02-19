package com.hospital.hospital_nearby_backend.repo;

import com.hospital.hospital_nearby_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}

