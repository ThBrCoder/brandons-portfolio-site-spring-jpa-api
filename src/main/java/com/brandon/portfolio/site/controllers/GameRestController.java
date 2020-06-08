package com.brandon.portfolio.site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brandon.portfolio.site.cnst.Constants;
import com.brandon.portfolio.site.entity.GamesDisplayList;
import com.brandon.portfolio.site.exception.GameNotFoundException;
import com.brandon.portfolio.site.response.GameSuccessResponse;
import com.brandon.portfolio.site.service.GamesServiceImpl;

@CrossOrigin
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
	
	/*
	@RequestMapping(value="/games",method=RequestMethod.OPTIONS)
	public ResponseEntity<?> collectionOptions() {
		return ResponseEntity.ok().allow(HttpMethod.GET,HttpMethod.POST,HttpMethod.PUT,HttpMethod.OPTIONS).build();
	}
	*/
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
	public synchronized GameSuccessResponse deleteGame(@PathVariable int gameId) {
		
		GamesDisplayList game = gamesServiceImpl.findGameById(gameId);
		
		if(game == null) {
			throw new GameNotFoundException("Game ID not found -" + gameId);
		}
		
		gamesServiceImpl.deleteGame(gameId);

		return new GameSuccessResponse(200,
				"Successfully deleted id: " + gameId, 
				System.currentTimeMillis());
	}
	
	
	// Post a game 
	// Need to test case for request with additional unnecessary parameters in json body postman
	//public synchronized String addGame(@ModelAttribute("game") GamesDisplayList game) {
	@PostMapping("/games")
	public synchronized GameSuccessResponse addGame(@RequestBody GamesDisplayList game) {
	
		
		game.setId(Constants.NEW_ITEM_ID);
		gamesServiceImpl.saveGame(game);
		
		return new GameSuccessResponse(200, 
				"Successfully added game: " + game.getTitle() + " - " + game.getYear(), 
				System.currentTimeMillis());
	}
	

	
	// Update game info
	@PutMapping("/games")
	public synchronized GameSuccessResponse updateGame(@RequestBody GamesDisplayList game) {

		GamesDisplayList checkGame = gamesServiceImpl.findGameById(game.getId());
		if(checkGame == null) {
			throw new GameNotFoundException("Game ID not found -" + game.getId());
		}		

		gamesServiceImpl.saveGame(game);
		
		return new GameSuccessResponse(200, 
				"Successfully updated game: " + game.getId() + " - " + game.getTitle() + " - " + game.getYear(),
				System.currentTimeMillis());
	}	
}
