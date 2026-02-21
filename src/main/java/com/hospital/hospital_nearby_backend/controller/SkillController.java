package com.hospital.hospital_nearby_backend.controller;

import com.hospital.hospital_nearby_backend.enums.Role;
import com.hospital.hospital_nearby_backend.enums.Skill;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sitehire/skills")
@CrossOrigin(origins = "*")
public class SkillController {

    @GetMapping
    public List<Skill> getSkills(@RequestParam Role role) {
        return Skill.getByRole(role);
    }
}
