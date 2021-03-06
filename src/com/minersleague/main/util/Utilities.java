package com.minersleague.main.util;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.minersleague.main.Main;
import com.minersleague.main.config.Statistics;
import com.minersleague.main.permissions.Groups;
import com.minersleague.main.permissions.World;

public class Utilities {
	
	public static Entity getEntityByID(org.bukkit.World world, UUID id) {
		for(Entity entity : world.getEntities()) {
			if(entity.getUniqueId().equals(id)) {
				return entity;
			}
		}
		return null;
	}
	
	public static void updatePermissions(Player p) {
		if(Groups.attachments.containsKey(p.getUniqueId())&&(Groups.attachments.get(p.getUniqueId())!=null)) {
			p.removeAttachment(Groups.attachments.get(p.getUniqueId()));
		}
		org.bukkit.World rawWorld = p.getLocation().getWorld();
		PermissionAttachment attachment = p.addAttachment(Main.plugin);
		World world = Groups.worlds.get(rawWorld.getName());
		HashSet<String> permissions = world.getGroupsAndPermissions().get(Statistics.getPlayerGroup(p.getName()));
		if(!permissions.isEmpty()&&world!=null) {
			for(String perm : permissions) {
				attachment.setPermission(perm, true);
			}
			Groups.attachments.put(p.getUniqueId(), attachment);
		}
	}
	
	public static boolean isOnlinePlayer(String player) {
		return Bukkit.getServer().getPlayer(player).isOnline();
	}
	
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
