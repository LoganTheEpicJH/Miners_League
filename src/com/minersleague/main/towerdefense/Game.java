package com.minersleague.main.towerdefense;

import java.util.ArrayList;

import org.bukkit.Location;

public class Game {

	private String name;
	private Location start;
	private Location end;
	private Location lobby;
	private Location game;
	private ArrayList<String> joined;
	private int towerAt;
	
	public Game() {}
	
	public Game(String name, Location start, Location end, Location lobby, Location game, ArrayList<String> playersIn) {
		joined = playersIn;
		this.name = name;
		this.start = start;
		this.end = end;
		this.lobby = lobby;
		this.game = game;
		towerAt = 0;
	}
	
	public int getNextTowerID() {
		towerAt++;
		return towerAt;
	}
	
	public void addPlayer(String player) {
		joined.add(player);
	}
	
	public boolean noNull() {
		if(name!=null&&start!=null&&end!=null&&lobby!=null&&game!=null) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getStart() {
		return start;
	}
	
	public Location getEnd() {
		return end;
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public Location getPlayground() {
		return game;
	}
	
	public ArrayList<String	> getPlayersPlaying() {
		return joined;
	}
	
}
