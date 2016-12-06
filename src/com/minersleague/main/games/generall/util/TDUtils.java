package com.minersleague.main.games.generall.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.minersleague.main.Main;
import com.minersleague.main.games.generall.PlayerStorage;
import com.minersleague.main.games.towerdefense.TDGame;
import com.minersleague.main.games.towerdefense.TowerDefenseHub;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimation;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimator;
import com.minersleague.main.games.towerdefense.mechanics.TDGameRunner;
import com.minersleague.main.games.towerdefense.tower.Tower;
import com.minersleague.main.games.towerdefense.tower.TowerBlock;
import com.minersleague.main.games.towerdefense.tower.TowerBuilder;

public class TDUtils {

	public static HashMap<String, TDGame> games;
	public static HashMap<String, PlayerStorage> gameIn;
	public static HashMap<String, Object> idLink;
	public static TowerDefenseHub hub;

	public static void loadGames() {
		games = new HashMap<String, TDGame>();
		gameIn = new HashMap<String, PlayerStorage>();
		idLink = new HashMap<String, Object>();
		File f = new File(Main.plugin.getDataFolder(), "games.yml");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch(IOException e) {}
		} else {
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			if(cfg.getStringList("game")!=null) {
				for(String name : cfg.getStringList("game")) {
					World pointWorld = Bukkit.getServer().getWorld(cfg.getString("game."+name+".points.world"));
					Location start = new Location(pointWorld, cfg.getDouble("game."+name+".start.x"), cfg.getDouble("game."+name+".start.y"), cfg.getDouble("game."+name+".start.z"));
					Location end = new Location(pointWorld, cfg.getDouble("game."+name+".end.x"), cfg.getDouble("game."+name+".end.y"), cfg.getDouble("game."+name+".end.z"));
					Location play = new Location(pointWorld, cfg.getDouble("game."+name+".play.x"), cfg.getDouble("game."+name+".play.y"), cfg.getDouble("game."+name+".play.z"));
					Location lobby = new Location(Bukkit.getServer().getWorld(cfg.getString("game."+name+".lobby.world")), cfg.getDouble("game."+name+".lobby.x"), cfg.getDouble("game."+name+".lobby.y"), cfg.getDouble("game."+name+".lobby.z"));
					TDUtils.games.put(name, new TDGame(name, start, end, lobby, play, new ArrayList<String>()));
				}
			}
		}
	}
	
	public static void stopAllGameActions(TDGame game) {
		for(String id : idLink.keySet()) {
			if(id.startsWith(game.getName())) {
				Object idLinkObj = idLink.get(id);
				if(id.contains("Animation")) {
					TDAnimation animation = (TDAnimation)idLinkObj;
					animation.stop();
					idLink.remove(id);
				}
				if(id.contains("GameStarter")) {
					TDGameRunner gs = (TDGameRunner)idLinkObj;
					gs.endGame();
					idLink.remove(id);
				}
				if(id.contains("Animator")) {
					TDAnimator animator = (TDAnimator)idLinkObj;
					animator.stop();
					idLink.remove(id);
				}
				if(id.contains("Thread")) {
					Thread thread = (Thread)idLinkObj;
					thread.interrupt();
					idLink.remove(id);
				}
				if(id.contains("TowerBuilder")) {
					TowerBuilder tb = (TowerBuilder)idLinkObj;
					tb.interrupt();
					idLink.remove(id);
				}
			}
		}
	}
	
	public static boolean checkSurroundingForTower(Location placing, Tower tower) {
		for(TowerBlock tb : tower.getBlocks()) {
			if(!placing.getWorld().getBlockAt(placing.clone().add(tb.x, tb.y, tb.z)).getType().equals(Material.AIR)) {
				return false;
			}
		}
		return true;
	}
	

}
