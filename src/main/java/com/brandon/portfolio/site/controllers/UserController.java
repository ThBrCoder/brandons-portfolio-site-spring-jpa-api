package com.brandon.portfolio.site.controllers;

import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandon.portfolio.site.entity.User;
import com.brandon.portfolio.site.service.UserServiceImpl;
import com.brandon.portfolio.site.util.JwtUtil;

@RestController
@CrossOrigin
public class UserController {
	
	
	private JwtUtil jwtUtil;
	private AuthenticationManager authenticationManager;
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	public UserController(AuthenticationManager authenticationManager, 
			JwtUtil jwtUtil,
			UserServiceImpl userServiceImpl) {
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.userServiceImpl = userServiceImpl;
	}
	

	/*
    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
    //public Map<String, Boolean> login(@RequestBody User user) {
        boolean value = user.getUsername().equals("admin") && user.getPassword().equals("pass");
        System.out.println("value == " + value);
        return value;
    	//System.out.println("Entering login...");
    	//boolean auth = user.getUsername().equals("admin") && user.getPassword().equals("pass");
    	//System.out.println("Auth == " + auth);
    	//return Collections.singletonMap("success", auth);
    }
    */
    
	/*
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {

    	String authToken = request.getHeader("Authorization")
    			.substring("Basic".length()).trim();

    	System.out.println("authToken == " + authToken);
    	return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
    */
    
    @GetMapping("/usertoken")
    public Map<String,Boolean> verifyToken(HttpServletRequest request) {	

    	String authToken = request.getHeader("Authorization").toString().substring("Bearer".length()).trim();

    	
    	String username = jwtUtil.extractUsername(authToken);
    	UserDetails userCredentials = userServiceImpl.loadUserByUsername(username);
    	Boolean isValidToken = jwtUtil.validateToken(authToken, userCredentials);

    	Map<String,Boolean> tokenValidity = new HashMap<String,Boolean>();
    	tokenValidity.put("tokenValidity", isValidToken);
    	return tokenValidity;
    }
    
    @PostMapping("/authenticate")
    public Map<String,String> createToken(@RequestBody User user) throws Exception{
    	try {
    		// This line will validate the username and password
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    	} catch (Exception ex) {
    		throw new Exception("Invalid authentication details");
    	}
    	String tokenValue = jwtUtil.generateToken(user.getUsername());
    	Map<String,String> token = new HashMap<String,String>();
    	token.put("token", tokenValue);
    	
    	return token;
    	
    }
    
    
}
