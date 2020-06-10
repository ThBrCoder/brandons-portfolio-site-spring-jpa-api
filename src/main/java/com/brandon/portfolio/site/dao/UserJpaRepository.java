package com.brandon.portfolio.site.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandon.portfolio.site.entity.UserCredentials;

public interface UserJpaRepository extends JpaRepository<UserCredentials, Integer>{
	UserCredentials findByUsername(String username);
}
