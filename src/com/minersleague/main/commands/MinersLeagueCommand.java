package com.minersleague.main.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minersleague.main.util.Utilities;

public abstract class MinersLeagueCommand implements CommandExecutor {

	private String name;

	public MinersLeagueCommand(String name) {
		this.name = name;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg1.getName().equalsIgnoreCase(name)) {
			if(onCommand(arg0, arg3)) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public abstract boolean onCommand(CommandSender sender, String[] args);
	
	public boolean isOnlinePlayer(String player) {
		return Utilities.isOnlinePlayer(player);
	}
	
	public Player getOnlinePlayer(String player) {
		return Utilities.getOnlinePlayer(player);
	}
	
	public OfflinePlayer getOfflinePlayer(String player) {
		return Utilities.getOfflinePlayer(player);
	}
	
	public Player commandSenderToPlayer(CommandSender sender) {
		if(sender instanceof Player) {
			return (Player)sender;
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
}
