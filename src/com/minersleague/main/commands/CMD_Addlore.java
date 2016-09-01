package com.minersleague.main.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class CMD_Addlore extends MinersLeagueCommand {
	public CMD_Addlore() {
		super("addlore");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to do this command!");
			return false;
		}

		Player p = (Player) sender;

		if (p.hasPermission("minersleague.rank.developer")) {
			if (args.length > 0) {
				PlayerInventory item = p.getInventory();
				ItemStack itemStack = item.getItemInMainHand();
				ItemMeta itemStackMeta = itemStack.getItemMeta();
				List<String> lores;
				if (itemStackMeta.getLore() == null) {
					lores = new ArrayList<String>();
				} else {
					lores = itemStackMeta.getLore();
				}
				lores.add(ChatColor.translateAlternateColorCodes('&', Arrays.stream(args).collect(Collectors.joining(" "))));
				itemStackMeta.setLore(lores);
				itemStack.setItemMeta(itemStackMeta);
				item.setItemInMainHand(itemStack);
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Arrays.stream(args).collect(Collectors.joining(" "))));
				p.sendMessage("");
				return true;
			} else {
				p.sendMessage("Error");
				return false;
			}
		} else {
			p.sendMessage("Error");
			return false;
		}
	}
}
