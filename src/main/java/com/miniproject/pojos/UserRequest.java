package com.miniproject.pojos;

import java.util.Set;

import com.miniproject.model.Skill;

public class UserRequest {
	public int id;
	public String name;
	public String study;
	public int year;
	public String gender;
	public String phone;
	public Set<Skill> skills;
}
