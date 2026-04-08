package com.srping_mvc_json_view_practicle.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srping_mvc_json_view_practicle.entity.User;
import com.srping_mvc_json_view_practicle.repository.UserRepository;
import com.srping_mvc_json_view_practicle.request.UserRequest;
import com.srping_mvc_json_view_practicle.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Long id) {
		Optional<User> user=userRepository.findById(id);
		return user.get();
	}

	@Override
	public User createUser(UserRequest user) {
		User user2=new User();
		user2.setEmail(user.getEmail());
		user2.setName(user.getName());
		return userRepository.save(user2);
	}

	@Override
	public User updateUser(UserRequest updated) {
		Optional<User> user=userRepository.findById(updated.getId());
		User user2=user.get();
		user2.setEmail(updated.getEmail());
		user2.setName(updated.getName());
		return user2;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
