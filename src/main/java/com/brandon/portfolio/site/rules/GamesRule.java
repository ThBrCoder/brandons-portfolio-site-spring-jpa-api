package com.brandon.portfolio.site.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.brandon.portfolio.site.entity.GamesDisplayList;
import com.brandon.portfolio.site.entity.GamesMasterList;

@Component
public class GamesRule {
	private List<GamesMasterList> gamesMasterList;
	
	public GamesRule() {
		gamesMasterList = new ArrayList<GamesMasterList>();
		GamesMasterList game1 = new GamesMasterList("The Legend of Zelda: Ocarina of Time",1998);
		GamesMasterList game2 = new GamesMasterList("Mystical Ninja Starring Goemon",1997);
		GamesMasterList game3 = new GamesMasterList("Doom 64",1997);
		GamesMasterList game4 = new GamesMasterList("Super Mario 64",1996);
		GamesMasterList game5 = new GamesMasterList("007 Goldeneye",1997);
		GamesMasterList game6 = new GamesMasterList("Resident Evil 2",1999);
		GamesMasterList game7 = new GamesMasterList("Quest 64",1998);
		GamesMasterList game8 = new GamesMasterList("Pilotwings 64",1996);
		GamesMasterList game9 = new GamesMasterList("Mario Kart 64",1996);
		GamesMasterList game10 = new GamesMasterList("Diddy Kong Racing",1997);
		GamesMasterList game11 = new GamesMasterList("Yoshi's Story",1997);
		GamesMasterList game12 = new GamesMasterList("Road Rash 64",1999);
		GamesMasterList game13 = new GamesMasterList("Star Fox 64",1997);
		GamesMasterList game14 = new GamesMasterList("Super Smash Bros.",1999);
		GamesMasterList game15 = new GamesMasterList("Bomberman 64",1997);
		GamesMasterList game16 = new GamesMasterList("Cruis'n USA",1996);
		GamesMasterList game17 = new GamesMasterList("Banjo-Kazooie",1998);
		GamesMasterList game18 = new GamesMasterList("Perfect Dark",2000);
		GamesMasterList game19 = new GamesMasterList("Mega Man 64",2001);
		GamesMasterList game20 = new GamesMasterList("Mischief Makers",1997);
		gamesMasterList.add(game1);
		gamesMasterList.add(game2);
		gamesMasterList.add(game3);
		gamesMasterList.add(game4);
		gamesMasterList.add(game5);
		gamesMasterList.add(game6);
		gamesMasterList.add(game7);
		gamesMasterList.add(game8);
		gamesMasterList.add(game9);
		gamesMasterList.add(game10);
		gamesMasterList.add(game11);
		gamesMasterList.add(game12);
		gamesMasterList.add(game13);
		gamesMasterList.add(game14);
		gamesMasterList.add(game15);
		gamesMasterList.add(game16);
		gamesMasterList.add(game17);
		gamesMasterList.add(game18);
		gamesMasterList.add(game19);
		gamesMasterList.add(game20);
	}
	
	public boolean isValidTitle(GamesDisplayList game) {

		if(game.getTitle() == null) {
			return false;
		}

		Optional<GamesMasterList> validGame = Optional.ofNullable(gamesMasterList.stream().filter(value -> 
			value.getTitle().equals(game.getTitle()) 
			&& value.getYear() == game.getYear()).findFirst().orElse(null));

		validGame.ifPresent(presentGame -> game.setTitle(presentGame.getTitle()));
		
		if(validGame.isPresent()) {
			
			return true;
		}
		
		return false;
	}
}
