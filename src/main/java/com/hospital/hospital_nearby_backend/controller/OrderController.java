package com.hospital.hospital_nearby_backend.controller;

import com.hospital.hospital_nearby_backend.entity.CartItem;
import com.hospital.hospital_nearby_backend.entity.Order;
import com.hospital.hospital_nearby_backend.repo.CartRepository;
import com.hospital.hospital_nearby_backend.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @PostMapping("/place")
    public ResponseEntity<Map<String, Object>> placeOrder() {

        // 1️⃣ Fetch cart items from DB
        List<CartItem> cartItems = cartRepository.findAll();

        if (cartItems.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Cart is empty")
            );
        }

        // 2️⃣ Calculate total
        BigDecimal total = cartItems.stream()
                .map(item -> item.getProduct()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3️⃣ Create Order
        Order order = Order.builder()
                .totalAmount(total)
                .status("PLACED")
                .build();

        orderRepository.save(order);

        // 4️⃣ Clear Cart
        cartRepository.deleteAll();

        // 5️⃣ Return response
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getId());
        response.put("totalAmount", total);
        response.put("status", "PLACED");

        return ResponseEntity.ok(response);
    }

}

