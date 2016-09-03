package com.minersleague.main.towerdefense;

import java.util.ArrayList;

public class Game {

	private StartPoint start;
	private EndPoint end;
	private ArrayList<Point> points;
	
	public Game() {}
	
	public Game(StartPoint start, EndPoint end, ArrayList<Point> points) {
		this.start = start;
		this.end = end;
		this.points = points;
	}
	
	public void setStart(StartPoint start) {
		this.start = start;
	}
	
	public void setEnd(EndPoint end) {
		this.end = end;
	}
	
	public StartPoint getStart() {
		return start;
	}
	
	public EndPoint getEnd() {
		return end;
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
}
