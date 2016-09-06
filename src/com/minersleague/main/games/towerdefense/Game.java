package com.minersleague.main.games.towerdefense;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.minersleague.main.util.Utilities;

public class Game {

	private String name;
	private Location start;
	private Location end;
	private Location lobby;
	private Location game;
	private ArrayList<String> joined;
	private int towerAt;
	private int dead;
	private int lifes;
	private int zombiesPassed;
	public boolean lost;
	
	public Game() {}
	
	public Game(String name, Location start, Location end, Location lobby, Location game, ArrayList<String> playersIn) {
		joined = playersIn;
		this.name = name;
		this.start = start;
		this.end = end;
		this.lobby = lobby;
		this.game = game;
		towerAt = 0;
		this.dead = 0; 
		this.lifes = 3;
		this.zombiesPassed = 0;
		lost = false;
	}
	
	public int getNextTowerID() {
		towerAt++;
		return towerAt;
	}
	
	@SuppressWarnings("deprecation")
	public void zombiePassed() {
		for(String s : joined) {
			Player p = Bukkit.getServer().getPlayer(s);
			/* ♥ */
			lifes--;
			if(lifes==0) {
				p.sendTitle(Utilities.color("&cGAME OVER"), Utilities.color("&aYou lost all Lifes"));
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 0);
				lost = true;
			} else {
				StringBuilder sb = new StringBuilder();
				for(int i = 1; i<=lifes; i++) {
					sb.append("♥");
				}
				p.sendTitle(Utilities.color("&cA Zombie reached the END!"), Utilities.color("&aLifes &4"+sb.toString()));
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 1, 0);
			}
		}
		zombiesPassed++;
	}
	
	public void zombieKilled() {
		dead++;
	}
	
	
	public int getZombiesPassed() {
		return zombiesPassed;
	}
	
	public int getZombiesKilled() {
		return dead;
	}
	
	public void addPlayer(String player) {
		joined.add(player);
	}
	
	public void removePlayer(String player) {
		joined.remove(player);
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
