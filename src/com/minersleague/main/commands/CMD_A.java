package com.minersleague.main.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minersleague.main.util.Utilities;

public class CMD_A extends MinersLeagueCommand {
	public CMD_A() {
		super("a");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		
		if (p.hasPermission("minersleague.rank.developer")) {
			if (args.length >= 2) {
				Player tP = Bukkit.getServer().getPlayer(args[0]);
				String message = ChatColor.translateAlternateColorCodes('&', Arrays.stream(args).collect(Collectors.joining(" "))).replace(args[0], "");
				
				tP.sendMessage(Utilities.color(p.getDisplayName() + "&r" + message));
				
				return true;
			}
		}
		
		return false;
	}
}
