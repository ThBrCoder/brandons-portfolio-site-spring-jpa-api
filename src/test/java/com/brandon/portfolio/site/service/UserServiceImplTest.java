package com.brandon.portfolio.site.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;

import com.brandon.portfolio.site.dao.UserJpaRepository;
import com.brandon.portfolio.site.entity.UserCredentials;

@SpringBootTest
public class UserServiceImplTest {

	@MockBean
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void login() {
		String username = "admin";
		String password = "abc123";
		User user = new User(username,password,new ArrayList<>());
		when(userJpaRepository.findByUsername(username)).thenReturn(new UserCredentials(username,password,1));
		assertEquals(user, this.userServiceImpl.loadUserByUsername(username));
	}
}
