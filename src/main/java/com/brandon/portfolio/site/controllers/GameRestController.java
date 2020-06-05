package com.brandon.portfolio.site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandon.portfolio.site.cnst.Constants;
import com.brandon.portfolio.site.entity.GamesDisplayList;
import com.brandon.portfolio.site.exception.GameNotFoundException;
import com.brandon.portfolio.site.service.GamesServiceImpl;

@RestController // May need to reset to normal Controller instead of REST controller (?)
@RequestMapping("/api")
public class GameRestController {
	
	// Add a field to store games service
	private GamesServiceImpl gamesServiceImpl;
	
	// Create an autowire constructor to inject the Games service
	@Autowired
	public GameRestController(GamesServiceImpl gamesServiceImpl) {
		this.gamesServiceImpl = gamesServiceImpl;
	}	
	
	// Get a list of games
	@GetMapping("/games")
	public List<GamesDisplayList> getAllGames() {
		
		return this.gamesServiceImpl.getAllGames();
	}
	
	@GetMapping("/games/{gameId}")
	public GamesDisplayList findGameById(@PathVariable int gameId) throws Exception {
		// Insert logic to validate ID is an integer and that ID for game exists
		
		GamesDisplayList game = gamesServiceImpl.findGameById(gameId);
		
		if(game == null) {
			throw new GameNotFoundException("Game ID not found -" + gameId);
		}
		
		return game;
	}
	
	// Delete a game
	@DeleteMapping("/games/{gameId}")
	public synchronized String deleteGame(@PathVariable int gameId) {
		
		GamesDisplayList game = gamesServiceImpl.findGameById(gameId);
		
		if(game == null) {
			throw new GameNotFoundException("Game ID not found -" + gameId);
		}
		
		gamesServiceImpl.deleteGame(gameId);
		
		return "Game deleted - id: " + gameId;
	}
	
	
	// Post a game 
	// Need to test case for request with additional unnecessary parameters in json body postman
	//public synchronized String addGame(@ModelAttribute("game") GamesDisplayList game) {
	@PostMapping("/games")
	public synchronized String addGame(@RequestBody GamesDisplayList game) {
	
		
		game.setId(Constants.NEW_ITEM_ID);
		gamesServiceImpl.saveGame(game);
		
		return "Game added - " + game.getTitle() + " " + game.getYear();
		// return "redirect:/games-api.html";
	}
	

	
	// Update game info
	@PutMapping("/games")
	public synchronized String updateGame(@RequestBody GamesDisplayList game) {

		GamesDisplayList checkGame = gamesServiceImpl.findGameById(game.getId());
		if(checkGame == null) {
			throw new GameNotFoundException("Game ID not found -" + game.getId());
		}		

		gamesServiceImpl.saveGame(game);
		
		return "Game updated - id: " + game.getId();
	}	
}