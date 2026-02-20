package com.hospital.hospital_nearby_backend.entity;

import com.hospital.hospital_nearby_backend.enums.Skill;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sitehire_worker_profiles")
public class WorkerProfile {

    @Id
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Skill skill;

    private Integer experienceYears;

    private Double latitude;
    private Double longitude;

    private Boolean available;
}
