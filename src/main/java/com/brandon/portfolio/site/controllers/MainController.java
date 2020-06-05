package com.brandon.portfolio.site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.brandon.portfolio.site.cnst.Constants;
import com.brandon.portfolio.site.entity.GamesDisplayList;
import com.brandon.portfolio.site.service.GamesServiceImpl;

@Controller
public class MainController {
	
	private GamesServiceImpl gamesServiceImpl;
	
	@Autowired
	public MainController(GamesServiceImpl gamesServiceImpl) {
		this.gamesServiceImpl = gamesServiceImpl;
	}
	
	@GetMapping("/")
	public String navHome() {
		return "home-main";
	}
	
	@GetMapping("/games-api")
	public String navGamesAPI(Model model) {
		
		List<GamesDisplayList> games = gamesServiceImpl.getAllGames();
		
		model.addAttribute("games",games);
		
		return "games-api";
	}

	/*
	@GetMapping("/games-api/game-form")
	public String navGamesAPIForm(Model model) {

		GamesDisplayList game = new GamesDisplayList();
		
		model.addAttribute("game",game);
		
		return "game-form";
	}
	
	// Post a game 
	// Need to test case for request with additional unnecessary parameters in json body postman
	
	@PostMapping("/games-api")
	public synchronized RedirectView addGame(@ModelAttribute("game") GamesDisplayList game) {
		
		game.setId(Constants.NEW_ITEM_ID);
		try {
			gamesServiceImpl.saveGame(game);
		} catch (Exception e) {
			
		}
		
		return new RedirectView("games-api");
	}
	*/
}
