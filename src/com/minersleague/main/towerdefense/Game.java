package com.minersleague.main.towerdefense;

import org.bukkit.Location;

public class Game {

	private String name;
	private Location start;
	private Location end;
	private Location lobby;
	private Location game;
	
	public Game() {}
	
	public Game(String name, Location start, Location end, Location lobby, Location game) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.lobby = lobby;
		this.game = game;
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
	
}
