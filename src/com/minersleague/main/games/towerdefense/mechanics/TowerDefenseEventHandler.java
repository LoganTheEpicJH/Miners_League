package com.minersleague.main.games.towerdefense.mechanics;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.TDGame;
import com.minersleague.main.games.towerdefense.TowerDefenseInventory;
import com.minersleague.main.games.towerdefense.tower.Tower;
import com.minersleague.main.games.towerdefense.tower.Towers;
import com.minersleague.main.util.Utilities;

public class TowerDefenseEventHandler implements Listener {

	@EventHandler
	public void gameZombieDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Zombie) {
			Zombie zombie = (Zombie)e.getEntity();
			if(zombie.getCustomName()!=null) {
				TDGame game = TDUtils.games.get(zombie.getCustomName().split("-")[1]);
				if(game!=null) {
					game.zombieKilled();
					e.setDroppedExp(0);
					e.getDrops().clear();
				}
			}
		}
	}

	@EventHandler
	public void onItemClick(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)||e.getAction().equals(Action.LEFT_CLICK_AIR)) {
			if(TDUtils.gameIn.get(e.getPlayer().getName())!=null) {
				if(TDUtils.gameIn.get(e.getPlayer().getName()).getPlayingStage()==PlayingStage.PLAYING) {
					e.setCancelled(true);
					e.getPlayer().openInventory(TowerDefenseInventory.getTowerDefenseInv());
				}
			}
		}
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			if(e.getInventory().getName().equals(Utilities.color("&cTowerDefense - Tower Menu"))) {
				e.setCancelled(true);
				Player p = (Player)e.getWhoClicked();
				ItemStack is = e.getCurrentItem();
				if(is!=null) {
					ItemMeta im = is.getItemMeta();
					if(im.hasDisplayName()) {
						if(im.hasLore()) {
							String searchedLore = null;
							for(String s : im.getLore()) {
								if(s.contains(Utilities.color("&cTowerSpawnID"))) {
									searchedLore = s;
									break;
								}
							}
							String spawnID = searchedLore.replace(Utilities.color("&cTowerSpawnID: &6"), "");
							// System.out.println(Utilities.color(spawnID));
							Tower tower = Towers.towers.get(spawnID);
							String prefix = null;
							if(TDUtils.gameIn.get(p.getName())!=null) {
								prefix = TDUtils.gameIn.get(p.getName()).getGameName();
								Towers.buildTowner(prefix, spawnID, 500, tower, p.getLocation());
								p.sendMessage(Utilities.color("&cTower "+spawnID+" is being created. Thread-Prefix: "+prefix));
							}
						}
					}
				}
			}
		}
	}

}
