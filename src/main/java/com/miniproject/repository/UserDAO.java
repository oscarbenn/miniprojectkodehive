package com.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	public User findById(int id);

	public List<User> findUsersBySkillsId(int skillId);

}
