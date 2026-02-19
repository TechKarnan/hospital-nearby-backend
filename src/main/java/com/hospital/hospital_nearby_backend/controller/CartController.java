package com.hospital.hospital_nearby_backend.controller;

import com.hospital.hospital_nearby_backend.entity.CartItem;
import com.hospital.hospital_nearby_backend.entity.Product;
import com.hospital.hospital_nearby_backend.repo.CartRepository;
import com.hospital.hospital_nearby_backend.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    @PostMapping("/{productId}")
    public CartItem addToCart(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = CartItem.builder()
                .product(product)
                .quantity(1)
                .build();

        return cartRepository.save(item);
    }

    @GetMapping
    public List<CartItem> viewCart() {
        return cartRepository.findAll();

    }
}

