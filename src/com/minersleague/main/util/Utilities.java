package com.minersleague.main.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Utilities {

	@SuppressWarnings("deprecation")
	public static boolean isOnlinePlayer(String player) {
		return Bukkit.getServer().getPlayer(player).isOnline();
	}
	
	@SuppressWarnings("deprecation")
	public static Player getOnlinePlayer(String player) {
		return Bukkit.getServer().getPlayer(player);
	}
	
	@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayer(String player) {
		return Bukkit.getServer().getOfflinePlayer(player);
	}
	
	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
}
