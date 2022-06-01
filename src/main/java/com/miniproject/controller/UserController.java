package com.miniproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.pojos.UserRequest;
import com.miniproject.model.User;
import com.miniproject.service.UserServiceDAO;

@Component
@RestController
public class UserController {
	
	@Autowired
	UserServiceDAO userServiceDao;
	
	@PostMapping("/user")
	public User addUserWithSkill(@RequestBody UserRequest userRequest) {
		return userServiceDao.addUserWithSkill(userRequest);
	}
	
	@GetMapping("/users")
	public ResponseEntity<Map<String, Object>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size
	        ) {
	    return userServiceDao.getAllUsers(page,size);
	}
	
	@GetMapping("/users/skills/{skillId}")
	public ResponseEntity<List<User>> getAllUsersBySkillId(@PathVariable(value = "skillId") int skillId) {
	    return userServiceDao.getAllUsersBySkillId(skillId);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
	    return userServiceDao.updateUser(id,user);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
	    return userServiceDao.deleteUser(id);
	}
	
}
