package com.minersleague.main.towerdefense;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minersleague.main.towerdefense.tower.Tower;
import com.minersleague.main.towerdefense.tower.Towers;
import com.minersleague.main.util.Utilities;

public class InventoryClickEvent implements Listener {

	@EventHandler
	public void onInvClick(org.bukkit.event.inventory.InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			if(e.getInventory().getName().equals(Utilities.color("&cTowerDefense - Tower Menu"))) {
				e.setCancelled(true);
				Player p = (Player)e.getWhoClicked();
				ItemStack is = e.getCurrentItem();
				if(is!=null) {
					ItemMeta im = is.getItemMeta();
					if(im.getDisplayName().equals(Utilities.color("&c&lBlastiod Furnace"))) {
						if(im.hasLore()) {
							String searchedLore = null;
							for(String s : im.getLore()) {
								if(s.contains(Utilities.color("&cTowerSpawnID"))) {
									searchedLore = s;
									break;
								}
							}
							String spawnID = searchedLore.replace(Utilities.color("&cTowerSpawnID: &6"), "");
							//System.out.println(Utilities.color(spawnID));
							Tower tower = Towers.towers.get(spawnID);
							String storePrefix = Utilities.gameIn.get(p.getName())+Utilities.games.get(Utilities.gameIn.get(p.getName())).getNextTowerID();
							Towers.buildTowner(storePrefix, 500, tower, p.getLocation());
							p.sendMessage(Utilities.color("&cTower "+spawnID+" is being created. Thread-Prefix: "+storePrefix));
						}
					}
				}
			}
		}
	}

}