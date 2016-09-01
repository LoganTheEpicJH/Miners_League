package com.minersleague.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minersleague.main.util.Utilities;

public class CMD_Announcement extends MinersLeagueCommand {
	public CMD_Announcement() {
		super("announce");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		
		if (p.hasPermission("minersleague.rank.admin")) {
			String message = args.toString();
			
			for (Player pp : Bukkit.getOnlinePlayers()) {
				pp.sendMessage("");
				pp.sendMessage(Utilities.color("&b&lANNOUNCEMENT"));
				pp.sendMessage(Utilities.color(message));
				pp.sendMessage(Utilities.color("&b&lANNOUNCEMENT"));
				pp.sendMessage("");
				pp.sendTitle(Utilities.color("&b&lANNOUNCEMENT"), Utilities.color(message));
				pp.playSound(pp.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 0);
			}
		}
		
		
		return false;
	}
}
