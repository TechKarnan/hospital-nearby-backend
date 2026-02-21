package com.hospital.hospital_nearby_backend.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Skill {

    // Worker Skills
    MASON(Role.WORKER),
    ELECTRICIAN(Role.WORKER),
    PLUMBER(Role.WORKER),
    CARPENTER(Role.WORKER),
    PAINTER(Role.WORKER),
    HELPER(Role.WORKER),

    // Engineer Skills
    BUILDER(Role.ENGINEER);

    private final Role role;

    Skill(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public static List<Skill> getByRole(Role role) {
        return Arrays.stream(Skill.values())
                .filter(skill -> skill.role == role)
                .collect(Collectors.toList());
    }
}