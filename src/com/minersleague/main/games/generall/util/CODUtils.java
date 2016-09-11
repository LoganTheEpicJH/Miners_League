package com.minersleague.main.games.generall.util;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.Player;

import com.minersleague.main.games.codwarfare.Area;
import com.minersleague.main.games.codwarfare.CODGameType;
import com.minersleague.main.games.codwarfare.CODSpawn;
import com.minersleague.main.games.codwarfare.CODStorage;

public class CODUtils {
	
	public static HashMap<String, CODStorage> gameIn;
	public static HashMap<String, Area> areas;
	
	public CODUtils() {
		gameIn = new HashMap<String, CODStorage>();
		areas = new HashMap<String, Area>();
	}
	
	public static String getTeamPlayerIsIn(Player p) {
		return gameIn.get(p.getName()).team;
	}
	
	public static CODStorage getPlayerStorage(Player p) {
		return gameIn.get(p.getName());
	}
	
	public static Area getAreaPlayerIsIn(Player p) {
		return areas.get(gameIn.get(p.getName()).getGameName());
	}
	
	public static CODSpawn getPlayerRespawn(Player p) {
		if(CODUtils.getAreaPlayerIsIn(p)!=null) {
			if(CODUtils.getAreaPlayerIsIn(p).type==CODGameType.TEAMS||CODUtils.getAreaPlayerIsIn(p).type==CODGameType.INFECTED) {
				return CODUtils.getAreaPlayerIsIn(p).teamSpawns.get(CODUtils.getTeamPlayerIsIn(p));
			}
			if(CODUtils.getAreaPlayerIsIn(p).type==CODGameType.FFA) {
				return CODUtils.getAreaPlayerIsIn(p).ffaSpawns.get(new Random().nextInt(CODUtils.getAreaPlayerIsIn(p).ffaSpawns.size()));
			}
		}
		return null;
	}
	
}
