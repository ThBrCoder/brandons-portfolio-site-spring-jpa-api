package com.brandon.portfolio.site.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brandon.portfolio.site.dao.UserJpaRepository;
import com.brandon.portfolio.site.entity.UserCredentials;

@Service
public class UserServiceImpl implements UserDetailsService{

	UserJpaRepository userJpaRepository;
	
	@Autowired
	public UserServiceImpl(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials user = userJpaRepository.findByUsername(username);
		
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
