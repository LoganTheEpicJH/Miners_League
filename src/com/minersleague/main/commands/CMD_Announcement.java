package com.minersleague.main.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

		if (p.hasPermission("minersleague.rank.developer")) {
			if (args.length > 0) {
				String message = ChatColor.translateAlternateColorCodes('&', Arrays.stream(args).collect(Collectors.joining(" ")));

				for (Player pp : Bukkit.getOnlinePlayers()) {
					pp.sendMessage("");
					pp.sendMessage(Utilities.color("&b&lANNOUNCEMENT"));
					pp.sendMessage(message);
					pp.sendMessage(Utilities.color("&b&lANNOUNCEMENT"));
					pp.sendMessage("");
					pp.sendTitle(Utilities.color("&b&lANNOUNCEMENT"), Utilities.color(message));
					pp.playSound(pp.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 0);
				}
				
				return true;
			}
		} else {
			p.sendMessage(Utilities.color("&c> &7You need rank &f[&cAdmin&f]&7."));
			return true;
		}

		return false;
	}
}
