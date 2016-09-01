package com.minersleague.main.permissions;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.permissions.PermissionAttachment;

import com.minersleague.main.Main;
import com.minersleague.main.config.Statistics;

public class PermissionJoinListener implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if(Groups.attachments.containsKey(p.getUniqueId())&&(Groups.attachments.get(p.getUniqueId())!=null)) {
			p.removeAttachment(Groups.attachments.get(p.getUniqueId()));
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
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

}
