package com.project.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Services.CustomerUserDetailServices;
import com.project.Utill.JwtRequest;
import com.project.Utill.JwtResponse;
import com.project.Utill.JwtUtill;

@RestController
public class HomeController {
	@Autowired
	private CustomerUserDetailServices customerUserDetailServices;
	@Autowired
	private JwtUtill jwtutill;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@RequestMapping(value = "/token",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			UserDetails userDetails=this.customerUserDetailServices.loadUserByUsername(jwtRequest.getUsername());
			String token=this.jwtutill.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponse(token));
		}catch (Exception e) {
			System.out.println(e);
			return (ResponseEntity<?>) ResponseEntity.status(403);
			
		}
		
	}
}
