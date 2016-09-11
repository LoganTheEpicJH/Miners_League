package com.minersleague.main.games.codwarfare;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

public class Area {

	public String name;
	public HashMap<String, CODSpawn> teamSpawns;
	public HashMap<String, ArrayList<String>> playersInTeam;
	public ArrayList<CODSpawn> ffaSpawns;
	public Location lobby;
	public CODGameType type;
	public int playersIn;
	
	public Area(String name, HashMap<String, CODSpawn> teamSpawns, ArrayList<CODSpawn> ffaSpawns, Location lobby, CODGameType type) {
		this.name = name;
		this.teamSpawns = teamSpawns;
		playersInTeam = new HashMap<String, ArrayList<String>>();
		this.ffaSpawns = ffaSpawns;
		this.lobby = lobby;
		this.type = type;
		playersIn = 0;
	}
	
}
