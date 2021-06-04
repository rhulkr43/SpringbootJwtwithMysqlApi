package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.UserRespository;
import com.project.entity.User;

@Service
public class UserServiceImp {

	@Autowired
	private UserRespository userRespository;
	
	public List<User> getUserList(){
		return userRespository.findAll();
	}
	
	public void addUser(User user) {
		userRespository.save(user);
	}
	
	public User getUserByid(Long id) {
		return userRespository.findById(id).get();
	}
	public void delete(Long id) {
		
		userRespository.deleteById(id);
		
	}
	
	public User getUserbyName(String username){
		return userRespository.findByUsername(username);
	}
}
