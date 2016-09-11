package com.minersleague.main.games.codwarfare;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minersleague.main.util.Utilities;

public class CODSetupInventory {

	public static String stString = "Select Team";
	
	public static Inventory selectTeam(Area area) {
		Inventory inv = Bukkit.getServer().createInventory(null, 9, "Select Team");
		ItemStack full = new ItemStack(Material.WOOL, 1, (short)5);
		ItemMeta fim = full.getItemMeta();
		fim.setDisplayName(Utilities.color("&7TEAM FULL"));
		List<String> flore = new ArrayList<String>();
		flore.add(Utilities.color("&e3/3"));
		fim.setLore(flore);
		full.setItemMeta(fim);
		if(area.type==CODGameType.TEAMS) {
			{
				if(area.playersInTeam.get("RED")==null) {
					area.playersInTeam.put("RED", new ArrayList<String>());
				}
				if(area.playersInTeam.get("GREEN")==null) {
					area.playersInTeam.put("GREEN", new ArrayList<String>());
				}
				if(area.playersInTeam.get("BLUE")==null) {
					area.playersInTeam.put("BLUE", new ArrayList<String>());
				}
				if(area.playersInTeam.get("BLUE")==null) {
					area.playersInTeam.put("BLUE", new ArrayList<String>());
				}
				if(area.playersInTeam.get("UNINFECTED")==null) {
					area.playersInTeam.put("UNINFECTED", new ArrayList<String>());
				}
				if(area.playersInTeam.get("INFECTED")==null) {
					area.playersInTeam.put("INFECTED", new ArrayList<String>());
				}
				if(area.ffaSpawns==null) {
					area.ffaSpawns = new ArrayList<CODSpawn>();
				}
				if(area.playersInTeam.get("RED").size()<3) {
					ItemStack is = new ItemStack(Material.WOOL, 1, (short)14);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(Utilities.color("&4RED TEAM"));
					List<String> lore = new ArrayList<String>();
					lore.add(Utilities.color("&e3 MAX."));
					im.setLore(lore);
					is.setItemMeta(im);
					inv.setItem(3, is);
				} else {
					inv.setItem(3, full);
				}
			}
			{
				if(area.playersInTeam.get("GREEN").size()<3) {
					ItemStack is = new ItemStack(Material.WOOL, 1, (short)5);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(Utilities.color("&2GREEN TEAM"));
					List<String> lore = new ArrayList<String>();
					lore.add(Utilities.color("&e3 MAX."));
					im.setLore(lore);
					is.setItemMeta(im);
					inv.setItem(4, is);
				} else {
					inv.setItem(4, full);
				}
			}
			{
				if(area.playersInTeam.get("BLUE").size()<3) {
					ItemStack is = new ItemStack(Material.WOOL, 1, (short)11);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(Utilities.color("&1BLUE TEAM"));
					List<String> lore = new ArrayList<String>();
					lore.add(Utilities.color("&e3 MAX."));
					im.setLore(lore);
					is.setItemMeta(im);
					inv.setItem(5, is);
				} else {
					inv.setItem(5, full);
				}
			}
		}
		if(area.type==CODGameType.INFECTED) {
			{
				if(area.playersInTeam.get("UNINFECTED").size()<7) {
					ItemStack is = new ItemStack(Material.WOOL, 1, (short)3);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(Utilities.color("&bUnINFECTED TEAM"));
					List<String> lore = new ArrayList<String>();
					lore.add(Utilities.color("&e7 MAX."));
					im.setLore(lore);
					is.setItemMeta(im);
					inv.setItem(3, is);
				} else {
					inv.setItem(3, full);
				}
			}
			{
				if(area.playersInTeam.get("INFECTED").size()<2) {
					ItemStack is = new ItemStack(Material.WOOL, 1, (short)3);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(Utilities.color("&2INFECTED TEAM"));
					List<String> lore = new ArrayList<String>();
					lore.add(Utilities.color("&e2 MAX. (At Start!)"));
					im.setLore(lore);
					is.setItemMeta(im);
					inv.setItem(5, is);
				} else {
					inv.setItem(5, full);
				}
			}
		}
		if(area.type==CODGameType.FFA) {
			{
				if(area.playersInTeam.get("FFA").size()<9) {
					ItemStack is = new ItemStack(Material.WOOL, 1, (short)10);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(Utilities.color("&dFFA"));
					List<String> lore = new ArrayList<String>();
					lore.add(Utilities.color("&e9 MAX."));
					im.setLore(lore);
					is.setItemMeta(im);
					inv.setItem(4, is);
				} else {
					inv.setItem(4, full);
				}
			}
		}
		return inv;
	}

	public static Inventory setupCodInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 9, "Setup COD");
		{
			ItemStack is = new ItemStack(Material.ARMOR_STAND);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lManage Spawns"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cManage the Spawns of your COD Game!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(0, is);
		}
		{
			ItemStack is = new ItemStack(Material.EMERALD);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lSet GameType"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cManage the Type of your COD Game!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(4, is);
		}
		{
			ItemStack is = new ItemStack(Material.BEACON);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lSet Lobby"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cManage the Lobby of your COD Game!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(8, is);
		}
		return inv;
	}

	public static Inventory setupCodGameTypeInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 9, "Setup COD GameType");
		{
			ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&4&lTE&2A&1MS"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cChange the GameType of COD Game to Teams!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(0, is);
		}
		{
			ItemStack is = new ItemStack(Material.MONSTER_EGG, 1, (short)EntityType.ZOMBIE.ordinal());
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&2&lINFECTED"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cChange the GameType of COD Game to &2INFECTED!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(4, is);
		}
		{
			ItemStack is = new ItemStack(Material.ARMOR_STAND);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&d&lFFA"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cChange the GameType of COD Game to &dFREE-FOR-ALL!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(8, is);
		}
		return inv;
	}

	public static Inventory setupCodSpawnsInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 9, "Setup COD Spawns");
		{
			ItemStack is = new ItemStack(Material.WOOL, 1, (short)14);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&c&lRed Spawn"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&cSet the Spawn of the RED Team!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(0, is);
		}
		{
			ItemStack is = new ItemStack(Material.WOOL, 1, (short)5);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&2&lGreen Spawn"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&2Set the Spawn of the GREEN Team!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(1, is);
		}
		{
			ItemStack is = new ItemStack(Material.WOOL, 1, (short)11);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&1&lBlue Spawn"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&1Set the Spawn of the BLUE Team!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(2, is);
		}
		{
			ItemStack is = new ItemStack(Material.WOOL, 1, (short)3);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&b&lUnINFECTED Spawn"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&bSet the Spawn of the UnINFECTED Team!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(4, is);
		}
		{
			ItemStack is = new ItemStack(Material.WOOL, 1, (short)13);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&a&lINFECTED Spawn"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&aSet the Spawn of the INFECTED Team!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(5, is);
		}
		{
			ItemStack is = new ItemStack(Material.WOOL, 1, (short)10);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(Utilities.color("&d&lFFA Spawn"));
			List<String> lore = new ArrayList<String>();
			lore.add(Utilities.color("&dSet a Spawn for Free-For-ALL!"));
			im.setLore(lore);
			is.setItemMeta(im);
			inv.setItem(7, is);
		}
		return inv;
	}

}
