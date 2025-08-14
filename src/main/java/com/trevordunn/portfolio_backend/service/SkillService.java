package com.trevordunn.portfolio_backend.service;

import com.trevordunn.portfolio_backend.model.Skill;
import com.trevordunn.portfolio_backend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill addSkill(Skill skill) {
        if (skillRepository.existsByName(skill.getName())) {
            throw new RuntimeException("Skill already exists");
        }
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}