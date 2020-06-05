package com.brandon.portfolio.site.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brandon.portfolio.site.cnst.Constants;
import com.brandon.portfolio.site.dao.GamesJpaRepository;
import com.brandon.portfolio.site.entity.GamesDisplayList;
import com.brandon.portfolio.site.exception.DisplayListFullException;
import com.brandon.portfolio.site.exception.DuplicateGameException;
import com.brandon.portfolio.site.exception.GameNotFoundException;
import com.brandon.portfolio.site.rules.GamesRule;

@Service
public class GamesServiceImpl implements GamesService {

	private GamesRule gamesRule;
	private GamesJpaRepository gamesJpaRepository;
	
	@Autowired
	public GamesServiceImpl(GamesJpaRepository gamesJpaRepository, GamesRule gamesRule) {
		this.gamesJpaRepository = gamesJpaRepository;
		this.gamesRule = gamesRule;
	}
	
	@Override
	public List<GamesDisplayList> getAllGames() {

		return this.gamesJpaRepository.findAll();
	}

	@Override
	public GamesDisplayList findGameById(int id) {

		Optional<GamesDisplayList> result = this.gamesJpaRepository.findById(id);
		
		GamesDisplayList game = null;
		
		if(!result.isPresent()) {
			throw new GameNotFoundException("Game ID not found - " + id);
		}
		
		game = result.get();
		
		return game;
	}

	@Override
	public void deleteGame(int id) {

		this.gamesJpaRepository.deleteById(id);
	}

	@Override
	public void saveGame(GamesDisplayList game) {
		// If ID == 0, then it's being added, otherwise it's being updated
		int listSize = 0;
		
		if(game.getId() == Constants.NEW_ITEM_ID) {
			listSize = getAllGames().size();
		}
		
		// Basically this is just saying - If the game matches one of the games in the list,
		// And the game title doesn't exist in the current list, proceed
		if(isValidTitle(game) == true && isDuplicate(game) == false) {
			if (listSize < Constants.TOTAL_GAMES_TO_DISPLAY) {
				this.gamesJpaRepository.save(game);
			}
			else if (listSize >= Constants.TOTAL_GAMES_TO_DISPLAY) {
				throw new DisplayListFullException("Games table is at max capacity. Please delete a game and try again.");
			}
		} 
		else {
			throw new DuplicateGameException("Invalid or duplicate title");
		}
	}

	// Helper method to determine if game being inserted is a duplicate that already
	// exists in the display list...
	private boolean isDuplicate(GamesDisplayList game) {
		List<GamesDisplayList> allDisplayedGames = getAllGames();

		for(GamesDisplayList displayedGame : allDisplayedGames) {
			if(displayedGame.getTitle().equalsIgnoreCase(game.getTitle()) 
					&& displayedGame.getYear() == game.getYear()) {
				return true;
			}
		}
			
		return false;
	}
	
	private boolean isValidTitle(GamesDisplayList game) {
		
		return gamesRule.isValidTitle(game);
	}
	
}
