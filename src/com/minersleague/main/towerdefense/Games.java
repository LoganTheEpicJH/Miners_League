package com.minersleague.main.towerdefense;

import java.util.HashMap;

public class Games {

	public static HashMap<String, Game> games;
	public static HashMap<Game, Boolean> running;
	
	public static void loadGames() {
		running = new HashMap<Game, Boolean>();
	}
	
}
