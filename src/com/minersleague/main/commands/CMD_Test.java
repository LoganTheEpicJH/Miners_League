package com.minersleague.main.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minersleague.main.config.Variables;
import com.minersleague.main.util.Utilities;

public class CMD_Test extends MinerLeagueCommand {

	public CMD_Test() {
		super("test");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		if(args.length==1) {
			if(isOnlinePlayer(args[0])) {
				Player target = getOnlinePlayer(args[0]);
				target.sendMessage(Utilities.color("&6"+sender.getName()+" >> You&f Hi"));
				sender.sendMessage(Utilities.color("&6You >> "+target.getName()+" &fHi"));
				return true;
			} else {
				sender.sendMessage(Utilities.color("&cThe Player &4"+args[0]+" &cis not Online!"));
				return true;
			}
		} else {
			sender.sendMessage(Variables.MISSING_ARGUMENTS_1);
			return true;
		}
	}

}
