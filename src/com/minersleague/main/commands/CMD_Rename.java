package com.minersleague.main.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMD_Rename extends MinersLeagueCommand {
	public CMD_Rename() {
		super("rename");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to do this command!");
			return false;
		}
		Player p = (Player)sender;
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length>0) {
				ItemStack item = p.getInventory().getItemInMainHand();
				ItemMeta itemStackMeta = item.getItemMeta();
				itemStackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Arrays.stream(args).collect(Collectors.joining(" "))));
				item.setItemMeta(itemStackMeta);
				p.sendMessage("Item renamed");
				return true;
			} else {
				p.sendMessage("error");
				return true;
			}
		} else {
			p.sendMessage("error");
			return false;
		}
	}
}
