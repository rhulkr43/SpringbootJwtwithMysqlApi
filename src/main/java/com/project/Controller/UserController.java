package com.project.Controller;

import java.lang.StackWalker.Option;
import java.util.Optional;

import javax.validation.Valid;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Services.CustomerUserDetailServices;
import com.project.Services.UserServiceImp;
import com.project.Utill.JwtRequest;
import com.project.Utill.JwtResponse;
import com.project.Utill.JwtUtill;
import com.project.entity.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private CustomerUserDetailServices customerUserDetailServices;
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	@GetMapping("/")
	public String Testw() {
		return "welcome";
	}
	
	@GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Object> getUserList() {
		
		return new ResponseEntity<Object>(userServiceImp.getUserList(), HttpStatus.OK);
	}

	
	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
	{
		User user2=userServiceImp.getUserbyName(user.getUsername());
		if(user2==null) {
			userServiceImp.addUser(user);
			return new ResponseEntity<Object>("User Add Successfull",HttpStatus.OK);
		}else {
			
			return new ResponseEntity<Object>(user.getUsername()+"  Already Existed Please Choose Another Username",HttpStatus.OK);
		}
		
	 }
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id){
		userServiceImp.delete(id);
		return new ResponseEntity<Object>("User Delete Successfull",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id){
		return new ResponseEntity<Object>(userServiceImp.getUserByid(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update",method= RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Object> update(@RequestBody User user){
		User user2=userServiceImp.getUserByid(user.getId());
		if(user2==null) {
			
			return new ResponseEntity<Object>("User Not Existed ",HttpStatus.OK);
				
		}else {
			user2.setEmail(user.getEmail());
			user.setPassword(user.getPassword());
			user.setUsername(user.getUsername());
			user.setRoles(user.getRoles());
			userServiceImp.addUser(user2);
			return new ResponseEntity<Object>("User Update Successfull",HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getUser/{username}",method = RequestMethod.GET)
	public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<Object>(userServiceImp.getUserbyName(username),HttpStatus.OK);
	}
}
