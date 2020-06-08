package com.brandon.portfolio.site.controllers;

import java.security.Principal;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandon.portfolio.site.entity.User;

@RestController
@CrossOrigin
public class UserController {

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
    //public Map<String, Boolean> login(@RequestBody User user) {
        return user.getUsername().equals("admin") && user.getPassword().equals("pass");
    	//System.out.println("Entering login...");
    	//boolean auth = user.getUsername().equals("admin") && user.getPassword().equals("pass");
    	//System.out.println("Auth == " + auth);
    	//return Collections.singletonMap("success", auth);
    }
    
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
    	System.out.println("Entering user request...");
    	String authToken = request.getHeader("Authorization")
    			.substring("Basic".length()).trim();
    	return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
