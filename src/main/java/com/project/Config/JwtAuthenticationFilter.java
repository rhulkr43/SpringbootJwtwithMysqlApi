package com.project.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.Services.CustomerUserDetailServices;
import com.project.Utill.JwtUtill;

import javassist.expr.NewArray;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private CustomerUserDetailServices customerUserDetailServices;
	@Autowired
	private JwtUtill jwtUtill;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String authentication = request.getHeader("Authorization");
		//String authentication=request.getHeader("Authorization");
		
		String username=null;
		String jwtToken=null;
		if(authentication!=null && authentication.startsWith("Bearer "))
		{
			jwtToken=authentication.substring(7);
			try {
				username=this.jwtUtill.extractUsername(jwtToken);
				
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Header Not Found");
				e.printStackTrace();
			}
			UserDetails userDetails=this.customerUserDetailServices.loadUserByUsername(username);
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Invalid Token");
			}
			
			
		}
		
		filterChain.doFilter(request, response);
	}
	

}
