package com.minersleague.main.util;

import java.util.HashMap;
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
import com.minersleague.main.games.towerdefense.Game;
import com.minersleague.main.games.towerdefense.TowerDefenseHub;
import com.minersleague.main.games.towerdefense.TowerDefensePlayerStorage;
import com.minersleague.main.games.towerdefense.mechanics.Animation;
import com.minersleague.main.games.towerdefense.mechanics.Animator;
import com.minersleague.main.games.towerdefense.mechanics.GameStarter;
import com.minersleague.main.games.towerdefense.tower.TowerBuilder;
import com.minersleague.main.games.towerdefense.tower.function.TowerFunction;
import com.minersleague.main.permissions.Groups;
import com.minersleague.main.permissions.World;

public class Utilities {

	public static HashMap<String, Game> games;
	public static HashMap<String, TowerDefensePlayerStorage> gameIn;
	public static HashMap<String, Object> idLink;
	public static TowerDefenseHub hub;
	
	public static void loadGames() {
		games = new HashMap<String, Game>();
		gameIn = new HashMap<String, TowerDefensePlayerStorage>();
		idLink = new HashMap<String, Object>();
	}
	
	public static Entity getEntityByID(org.bukkit.World world, UUID id) {
		for(Entity entity : world.getEntities()) {
			if(entity.getUniqueId().equals(id)) {
				return entity;
			}
		}
		return null;
	}
	
	public static void stopAllGameActions(Game game) {
		for(String id : Utilities.idLink.keySet()) {
			if(id.startsWith(game.getName())) {
				Object idLinkObj = Utilities.idLink.get(id);
				if(id.contains("TowerBuilder")) {
					TowerBuilder tb = (TowerBuilder)idLinkObj;
					tb.interrupt();
				}
				if(id.contains("Animation")) {
					Animation animation = (Animation)idLinkObj;
					animation.stop();
				}
				if(id.contains("GameStarter")) {
					GameStarter gs = (GameStarter)idLinkObj;
					gs.endGame();
				}
				if(id.contains("Animator")) {
					Animator animator = (Animator)idLinkObj;
					animator.stop();
				}
				if(id.contains("Thread")) {
					Thread thread = (Thread)idLinkObj;
					thread.interrupt();
				}
				if(id.contains("TowerFunction")) {
					TowerFunction tf = (TowerFunction)idLinkObj;
					tf.repeating = false;
				}
			}
		}
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
