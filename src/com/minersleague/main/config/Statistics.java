package com.minersleague.main.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.minersleague.main.permissions.Group;
import com.minersleague.main.permissions.Groups;

public class Statistics {		
	private Statistics() {}
	
	static Statistics instance = new Statistics();
	
	static Plugin p;
	
	static FileConfiguration stats;
	static File sfile;
	
	public static void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		sfile = new File(p.getDataFolder(), "stats.yml");
		
		if (!sfile.exists()) {
			try {
				sfile.createNewFile();
			} catch(IOException e) {
				
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create stats.yml!");
			}
		}
		
		
		stats = YamlConfiguration.loadConfiguration(sfile);
	}
	
	public static FileConfiguration getData() {
		return stats;
	}
	
	public static void saveData() {
		try {
			stats.save(sfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save stats.yml!");
		}
	}
	
	public static Group getPlayerGroup(String player) {
		return Groups.groups.get(getData().getString("Bukkit.Players."+player+".rank"));
	}
 	
	public static int getPlayerCoins(String player) {
		return getData().getInt("Bukkit.Players."+player+".coins");
	}
	
	public static void setPlayerCoins(String player, int coins) {
		getData().set("Bukkit.Players."+player+".coins", coins);
		saveData();
		reloadData();
	}
	
	public static void reloadData() {
		stats = YamlConfiguration.loadConfiguration(sfile);
	}
	
	public static PluginDescriptionFile getDesc() {
		return p.getDescription();
	}
}

