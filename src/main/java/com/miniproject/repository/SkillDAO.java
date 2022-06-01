package com.miniproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.model.Skill;

@Repository
public interface SkillDAO extends JpaRepository<Skill, Integer> {

	public Skill findById(int id);
}
