package com.minersleague.main.towerdefense;

import java.util.ArrayList;

import org.bukkit.Location;

public class Game {

	private Location start;
	private Location end;
	private Location lobby;
	private Location game;
	private ArrayList<Point> points;
	
	public Game() {}
	
	public Game(Location start, Location end, ArrayList<Point> points, Location lobby, Location game) {
		this.start = start;
		this.end = end;
		this.points = points;
		this.lobby = lobby;
		this.game = game;
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
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
}
