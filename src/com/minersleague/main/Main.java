package com.minersleague.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.AsyncCatcher;

import com.minersleague.main.commands.CMD_Addlore;
import com.minersleague.main.commands.CMD_Announcement;
import com.minersleague.main.commands.CMD_GameTowerDefense;
import com.minersleague.main.commands.CMD_Rename;
import com.minersleague.main.commands.CMD_TDGameTabCompleter;
import com.minersleague.main.commands.MinersLeagueCommand;
import com.minersleague.main.config.Statistics;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.mechanics.TowerDefenseEventHandler;
import com.minersleague.main.games.towerdefense.tower.Towers;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		Statistics.setup(plugin);
		AsyncCatcher.enabled = false;
		//Groups.init();
		Towers.loadTowners();
		TDUtils.loadGames();
		//new CODUtils();
		
		//Commands
		//registerCommand(new CMD_Test());
		registerCommand(new CMD_Announcement());
		registerCommand(new CMD_Rename());
		registerCommand(new CMD_Addlore());
		registerCommand(new CMD_GameTowerDefense());
		getCommand("towerdefense").setTabCompleter(new CMD_TDGameTabCompleter());
		//registerCommand(new CMD_COD());
		
		//Listeners
		//registerListener(new PlayerWorldChangeEvent());
		registerListener(new TowerDefenseEventHandler());
		//registerListener(new CODEventHandler());
		
	}

	@Override
	public void onDisable() {}
	
	public void registerCommand(MinersLeagueCommand cmd) {
		getCommand(cmd.getName()).setExecutor(cmd);
	}
	
	public void registerListener(Listener listener) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, this);
	}
	
}
