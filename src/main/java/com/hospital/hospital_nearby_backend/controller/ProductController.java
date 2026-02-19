package com.hospital.hospital_nearby_backend.controller;

import com.hospital.hospital_nearby_backend.entity.Product;
import com.hospital.hospital_nearby_backend.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}

