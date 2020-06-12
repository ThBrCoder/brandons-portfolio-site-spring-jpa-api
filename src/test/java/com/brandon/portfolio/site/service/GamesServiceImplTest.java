package com.brandon.portfolio.site.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.brandon.portfolio.site.dao.GamesJpaRepository;
import com.brandon.portfolio.site.entity.GamesDisplayList;
import com.brandon.portfolio.site.exception.DisplayListFullException;
import com.brandon.portfolio.site.exception.DuplicateGameException;

@SpringBootTest
public class GamesServiceImplTest {

	
	@Autowired
	private GamesServiceImpl gamesServiceImpl;
	
	@MockBean
	private GamesJpaRepository gamesJpaRepository;
	
	List<GamesDisplayList> gamesDisplayList;
	
	@BeforeEach
	public void testPrep() {	
		this.gamesDisplayList = new ArrayList<GamesDisplayList>();
		GamesDisplayList game1 = new GamesDisplayList(1,"The Legend of Zelda: Ocarina of Time",1998);
		GamesDisplayList game2 = new GamesDisplayList(2,"Mystical Ninja Starring Goemon",1997);
		GamesDisplayList game3 = new GamesDisplayList(3,"Doom 64",1997);
		GamesDisplayList game4 = new GamesDisplayList(4,"Super Mario 64",1996);
		GamesDisplayList game5 = new GamesDisplayList(5,"007 Goldeneye",1997);
		GamesDisplayList game6 = new GamesDisplayList(6,"Resident Evil 2",1999);
		GamesDisplayList game7 = new GamesDisplayList(7,"Quest 64",1998);
		GamesDisplayList game8 = new GamesDisplayList(8,"Pilotwings 64",1996);
		GamesDisplayList game9 = new GamesDisplayList(9,"Mario Kart 64",1996);
		gamesDisplayList.add(game1);
		gamesDisplayList.add(game2);
		gamesDisplayList.add(game3);
		gamesDisplayList.add(game4);
		gamesDisplayList.add(game5);
		gamesDisplayList.add(game6);
		gamesDisplayList.add(game7);
		gamesDisplayList.add(game8);
		gamesDisplayList.add(game9);
	}
	

	@Test()
	public void getGames() {
		when(gamesJpaRepository.findAll())
		.thenReturn(Stream.of(new GamesDisplayList(1,"The Legend of Zelda: Ocarina of Time",1998), 
				new GamesDisplayList(2,"Mystical Ninja Starring Goemon",1997),
				new GamesDisplayList(3,"Doom 64",1997),
				new GamesDisplayList(4,"Super Mario 64",1996),
				new GamesDisplayList(5,"007 Goldeneye",1997),
				new GamesDisplayList(6,"Resident Evil 2",1999),
				new GamesDisplayList(7,"Quest 64",1998),
				new GamesDisplayList(8,"Pilotwings 64",1996),
				new GamesDisplayList(9,"Mario Kart 64",1996))
				.collect(Collectors.toList()));
		assertEquals(9, gamesServiceImpl.getAllGames().size());
	}
	
	@Test()
	public void findGameById() {
		int id = 1;

		when(gamesJpaRepository.findById(id))
		.thenReturn(Optional.of(new GamesDisplayList(1,"The Legend of Zelda: Ocarina of Time",1998)));
		assertEquals(1, gamesServiceImpl.findGameById(id).getId());
	}
	
	@Test()
	public void removeGameExists() {
		int id = 1;
		gamesJpaRepository.deleteById(id);
		verify(gamesJpaRepository, times(1)).deleteById(id);
	}
	

	@Test()
	public void addGame() {
		int id = 10;
		GamesDisplayList game = new GamesDisplayList(id,"Mischief Makers",1997);
		when(gamesJpaRepository.save(game)).thenReturn(game);
		assertEquals(game, gamesJpaRepository.save(game));
	}
	
	@Test
	public void addGameToFullList() {
		int id = 0;
		GamesDisplayList game = new GamesDisplayList(id,"Mischief Makers",1997);
		when(gamesJpaRepository.findAll()).thenReturn(Stream.of(
				new GamesDisplayList(1,"The Legend of Zelda: Ocarina of Time",1998), 
				new GamesDisplayList(2,"Mystical Ninja Starring Goemon",1997),
				new GamesDisplayList(3,"Doom 64",1997),
				new GamesDisplayList(4,"Super Mario 64",1996),
				new GamesDisplayList(5,"007 Goldeneye",1997),
				new GamesDisplayList(6,"Resident Evil 2",1999),
				new GamesDisplayList(7,"Quest 64",1998),
				new GamesDisplayList(8,"Pilotwings 64",1996),
				new GamesDisplayList(9,"Mario Kart 64",1996),
				new GamesDisplayList(10,"Cruis'n USA",1996))
				.collect(Collectors.toList()));
		
		assertThrows(DisplayListFullException.class,() -> gamesServiceImpl.saveGame(game));
	}
	
	@Test
	public void addDuplicateGameToList() {
		int id = 0;
		GamesDisplayList game = new GamesDisplayList(id,"The Legend of Zelda: Ocarina of Time",1998);
		when(gamesJpaRepository.findAll()).thenReturn(Stream.of(
				new GamesDisplayList(1,"The Legend of Zelda: Ocarina of Time",1998))
				.collect(Collectors.toList()));
		
		assertThrows(DuplicateGameException.class,() -> gamesServiceImpl.saveGame(game));
	}
	
	
}
