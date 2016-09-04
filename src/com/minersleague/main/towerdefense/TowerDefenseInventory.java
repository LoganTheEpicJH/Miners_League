package com.minersleague.main.towerdefense;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minersleague.main.util.Utilities;

public class TowerDefenseInventory {

	public static Inventory getTowerDefenseInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 9*6, Utilities.color("&cTowerDefense - Tower Menu"));
		{
			ItemStack is = new ItemStack(Material.TNT);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lBlastiod Furnace"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cBuild-Time: &613 Seconds"));
			lore.add(Utilities.color("&cType: &6Multi-Damge"));
			lore.add(Utilities.color("&cRange: &67 Blocks"));
			lore.add(Utilities.color("&cTowerSpawnID: &6blastiod"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(10, is);
		}
		return inv;
	}
	
}
