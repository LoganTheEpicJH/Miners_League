package com.minersleague.main.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Announcement extends MinersLeagueCommand {
	public CMD_Announcement() {
		super("announce");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		
		if (p.hasPermission("")) {
			
		}
		
		
		return false;
	}
}
