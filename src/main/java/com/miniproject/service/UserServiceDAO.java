package com.miniproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miniproject.model.Skill;
import com.miniproject.model.User;
import com.miniproject.pojos.UserRequest;
import com.miniproject.repository.SkillDAO;
import com.miniproject.repository.UserDAO;

@Service
public class UserServiceDAO {
	
	@Autowired
	UserDAO userRepository;
	
	@Autowired
	SkillDAO skillRepository;
	
	public UserServiceDAO() {}
	
	public User addUserWithSkill(UserRequest userRequest) {
		User user = new User();
		user.setId(userRequest.id);
		user.setName(userRequest.name);
		user.setStudy(userRequest.study);
		user.setYear(userRequest.year);
		user.setGender(userRequest.gender);
		user.setPhone(userRequest.phone);
		user.setSkills(userRequest.skills
			.stream()
			.map(skill -> {
				Skill s = skill;
				if(s.getId() > 0) {
					s = skillRepository.findById(s.getId());
				}
				s.addUser(user);
				return s;
			})
			.collect(Collectors.toSet()));
			
			return userRepository.save(user);
	}

	public ResponseEntity<Map<String, Object>> getAllUsers(int page, int size) {
		List<User> users = new ArrayList<User>();
		Pageable paging = PageRequest.of(page, size);
		
		Page<User> pageUser;
		pageUser = userRepository.findAll(paging);
		
		users = pageUser.getContent();
		
		Map<String, Object> response = new HashMap<>();
		response.put("users", users);
	    response.put("currentPage", pageUser.getNumber());
	    response.put("totalItems", pageUser.getTotalElements());
	    response.put("totalPages", pageUser.getTotalPages());
	    
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<List<User>> getAllUsersBySkillId(int skillId) {
		if (!skillRepository.existsById(skillId)) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<User> users = userRepository.findUsersBySkillsId(skillId);
	
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	public ResponseEntity<User> updateUser(int id, User user) {
		User _user = userRepository.findById(id);
			_user.setName(user.getName());
			_user.setStudy(user.getStudy());
			_user.setYear(user.getYear());
			_user.setGender(user.getGender());
			_user.setPhone(user.getPhone());
			_user.setSkills(user.getSkills());
		    
		return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
	}

	public ResponseEntity<HttpStatus> deleteUser(int id) {
		userRepository.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}

}
