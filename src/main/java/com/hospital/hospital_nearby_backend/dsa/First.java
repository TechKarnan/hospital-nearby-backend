package com.hospital.hospital_nearby_backend.dsa;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class First {

    public List<Product> mergeCatalogs(List<List<Product>> supplierCatalogs) {

        Map<Long, Product> merged = new HashMap<>();

        for (List<Product> supplier : supplierCatalogs) {
            for (Product p : supplier) {

                merged.merge(p.getId(), p, (existing, incoming) -> {

                    if (incoming.getPrice() < existing.getPrice())
                        existing.setPrice(incoming.getPrice());

                    if (incoming.getCommission() > existing.getCommission())
                        existing.setCommission(incoming.getCommission());

                    return existing;
                });
            }
        }

        return new ArrayList<>(merged.values());
    }


}


@Data
@AllArgsConstructor
class Product{
    private Long id;
    private String name;
    private double price;
    private double commission;
}


