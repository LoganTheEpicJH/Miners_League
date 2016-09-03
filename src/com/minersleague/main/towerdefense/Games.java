package com.minersleague.main.towerdefense;

import java.util.HashMap;

public class Games {

	public static HashMap<String, Game> games;
	public static HashMap<Game, Boolean> running;
	
	public static void loadGames() {
		games = new HashMap<String, Game>();
		running = new HashMap<Game, Boolean>();
	}
	
}
