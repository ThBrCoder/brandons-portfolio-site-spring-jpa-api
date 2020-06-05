package com.brandon.portfolio.site.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandon.portfolio.site.entity.GamesDisplayList;

public interface GamesJpaRepository extends JpaRepository<GamesDisplayList, Integer>{
	
}
