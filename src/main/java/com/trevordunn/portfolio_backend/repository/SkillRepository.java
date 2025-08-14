package com.trevordunn.portfolio_backend.repository;

import com.trevordunn.portfolio_backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    boolean existsByName(String name);
}