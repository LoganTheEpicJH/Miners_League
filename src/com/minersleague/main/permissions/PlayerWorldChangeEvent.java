package com.minersleague.main.permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import com.minersleague.main.util.Utilities;

public class PlayerWorldChangeEvent implements Listener {

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e) {
		Utilities.updatePermissions(e.getPlayer());
	}
	
}
