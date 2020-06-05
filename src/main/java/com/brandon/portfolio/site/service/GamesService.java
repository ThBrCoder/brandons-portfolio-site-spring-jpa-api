package com.brandon.portfolio.site.service;

import java.util.List;

import com.brandon.portfolio.site.entity.GamesDisplayList;

public interface GamesService {

	// Create field to inject Games DAO
	// Method to get list of games
	// Method to get a game by id
	// Method to post/put to add/update a game -- Check validity here and total < 10
	// Method to delete a game

	public List<GamesDisplayList> getAllGames();
	public GamesDisplayList findGameById(int id);
	public void deleteGame(int id);
	public void saveGame(GamesDisplayList game);
}
