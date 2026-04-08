package com.srping_mvc_json_view_practicle.service;

import java.util.List;

import com.srping_mvc_json_view_practicle.entity.User;
import com.srping_mvc_json_view_practicle.request.UserRequest;

public interface UserService {

	public List<User> getAllUsers();

	public User getUser(Long id);
	
	 public User createUser(UserRequest user);
	 
	 public User updateUser(UserRequest updated);
	 
	 public void deleteUser(Long id);
}
