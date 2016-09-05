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

	//
	public static Inventory getTowerDefenseInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 9*6, Utilities.color("&cTowerDefense - Tower Menu"));
		{
			ItemStack is = new ItemStack(Material.TNT);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lBlastiod Furnace"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cBuild-Time: &613 Seconds"));
			lore.add(Utilities.color("&cType: &6Multi-Damge"));
			lore.add(Utilities.color("&cRange: &65 Blocks"));
			lore.add(Utilities.color("&cTowerSpawnID: &6blastiod"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(10, is);
		}
		{
			ItemStack is = new ItemStack(Material.END_CRYSTAL);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lLow Powered Tesla"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cBuild-Time: &65.5 Seconds"));
			lore.add(Utilities.color("&cType: &6Focus-Damge"));
			lore.add(Utilities.color("&cRange: &63.5 Blocks"));
			lore.add(Utilities.color("&cTowerSpawnID: &6lpt"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(12, is);
		}
		{
			ItemStack is = new ItemStack(Material.END_CRYSTAL);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lReverse Mob Generator"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cBuild-Time: &69.5 Seconds"));
			lore.add(Utilities.color("&cType: &6Infection-Damge"));
			lore.add(Utilities.color("&cRange: &63.5 Blocks"));
			lore.add(Utilities.color("&cTowerSpawnID: &6rmg"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(14, is);
		}
		return inv;
	}
	
}
