package com.hospital.hospital_nearby_backend.entity;

import com.hospital.hospital_nearby_backend.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sitehire_users")
public class SiteHireUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String mpin;

    @Enumerated(EnumType.STRING)
    private Role role;
}
