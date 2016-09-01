package com.minersleague.main.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_A extends MinersLeagueCommand {
	public CMD_A() {
		super("a");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		
		if (p.hasPermission("minersleague.rank.developer")) {
			if (args.length >= 2) {
				
			}
		}
		
		return false;
	}
}
