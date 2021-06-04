package com.project.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class CustomerUserDetailServices implements UserDetailsService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImp userServiceImp;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		com.project.entity.User user=userServiceImp.getUserbyName(username);
		
		if(username.equals(user.getUsername())) {
			return new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),new ArrayList<>());
		}else {
			return (UserDetails) new UsernameNotFoundException("User Not Found");
		}
		
	}

	
}
