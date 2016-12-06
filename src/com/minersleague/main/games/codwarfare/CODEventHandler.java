package com.minersleague.main.games.codwarfare;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minersleague.main.Main;
import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.util.CODUtils;
import com.minersleague.main.util.ActionbarMessage;
import com.minersleague.main.util.Utilities;

public class CODEventHandler implements Listener {

	@EventHandler
	public void onGunShoot(PlayerInteractEvent e) {
		if(CODUtils.gameIn.get(e.getPlayer().getName())!=null) {
			if(CODUtils.getPlayerStorage(e.getPlayer()).getPlayingStage()==PlayingStage.PLAYING) {
				if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK) {
					if(Guns.getGunfromMaterial(e.getPlayer().getInventory().getItemInMainHand().getType())!=null) {
						e.getPlayer().spawnParticle(Particle.FLAME, e.getPlayer().getLocation().clone().add(0, 1.5d, 0), 1);
						e.getPlayer().spawnParticle(Particle.SMOKE_NORMAL, e.getPlayer().getLocation().clone().add(0, 1.5d, 0), 1);
						e.getPlayer().setFoodLevel(20);
						Guns.shootGun(e.getPlayer());
						// ActionbarMessage.sendAction(e.getPlayer(), Utilities.color("&4&l■■■■■■■■■■"));
					}
				}
				if(e.getAction()==Action.LEFT_CLICK_AIR||e.getAction()==Action.LEFT_CLICK_BLOCK) {
					if(e.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
						e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
					} else {
						e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 20));
					}
				}
			}
		}
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if(e.getEntity().getType()==EntityType.PLAYER) {
			Player damaged = (Player)e.getEntity();
			if(CODUtils.getAreaPlayerIsIn(damaged)!=null) {
				if(CODUtils.getPlayerStorage(damaged).getPlayingStage()==PlayingStage.PLAYING) {
					if(e.getCause()==DamageCause.PROJECTILE) {
						if(e.getDamager().getType()==EntityType.SNOWBALL) {
							if(e.getDamager().hasMetadata(GunMetaData.GUN_SHOOTER.key)) {
								e.setCancelled(true);
								System.out.println("Event Cancelled");
								Snowball ball = (Snowball)e.getDamager();
								Player shooter = Bukkit.getServer().getPlayer(ball.getMetadata(GunMetaData.GUN_SHOOTER.key).get(0).asString());
								String teamDamaged = CODUtils.getTeamPlayerIsIn(damaged);
								String teamShooter = CODUtils.getTeamPlayerIsIn(shooter);
								if(!teamDamaged.equals(teamShooter)&&!teamDamaged.equals("FFA")) {
									shooter.playSound(shooter.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
									damaged.setLevel(damaged.getLevel()-ball.getMetadata(GunMetaData.GUN_DAMAGE.key).get(0).asInt());
									if(CODUtils.assist.get(damaged.getName())!=null) {
										CODUtils.assist.put(damaged.getName(), new HashMap<String, Integer>());
										CODUtils.assist.get(damaged.getName()).put(shooter.getName(), ball.getMetadata(GunMetaData.GUN_DAMAGE.key).get(0).asInt());
									} else {
										CODUtils.assist.get(damaged.getName()).put(shooter.getName(), ball.getMetadata(GunMetaData.GUN_DAMAGE.key).get(0).asInt());
									}
									shooter.setMetadata("ml_coins", new FixedMetadataValue(Main.plugin, shooter.getMetadata("ml_coins").get(0).asInt()+1));
									if(damaged.getLevel()<=0) {
										for(String s : CODUtils.assist.get(damaged.getName()).keySet()) {
											if(CODUtils.assist.get(damaged.getName()).get(s)>=28&&!s.equals(shooter.getName())) {
												Bukkit.getServer().getPlayer(s).setMetadata("ml_coins", new FixedMetadataValue(Main.plugin, Bukkit.getServer().getPlayer(s).getMetadata("ml_coins").get(0).asInt()+2));
												ActionbarMessage.showMessage(Bukkit.getServer().getPlayer(s), Utilities.color("&1&lYOU &r&fassisted to kill &c&l"+damaged.getName()));
											}
										}
										shooter.setMetadata("ml_coins", new FixedMetadataValue(Main.plugin, shooter.getMetadata("ml_coins").get(0).asInt()+4));
										ActionbarMessage.showMessage(shooter, Utilities.color("&1&lYOU &r&fkilled &c&l"+damaged.getName()));
										damaged.setHealth(0.0d);
									} else {

									}
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(CODUtils.gameIn.get(e.getEntity().getName())!=null) {
			if(CODUtils.gameIn.get(e.getEntity().getName()).getPlayingStage()==PlayingStage.PLAYING) {
				e.setDeathMessage("");
				e.getEntity().setHealth(20.0d);
				e.setKeepInventory(true);
				e.setKeepLevel(true);
				e.getEntity().setExp(0f);
				e.getEntity().setLevel(100);
				e.getEntity().teleport(CODUtils.getPlayerRespawn(e.getEntity()).spawn);
			}
		}
	}

	@EventHandler
	public void onCODSetup(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player)e.getWhoClicked();
			if(CODUtils.gameIn.get(p.getName())!=null) {
				if(e.getInventory().getName().equals(CODSetupInventory.stString)) {
					Area area = CODUtils.getAreaPlayerIsIn(p);
					if(area.type==CODGameType.TEAMS) {
						if(e.getSlot()==3) {

						}
					}
				}
				if(e.getInventory().getName().equals(CODSetupInventory.setupCodGameTypeInv().getName())||e.getInventory().getName().equals(CODSetupInventory.setupCodInv().getName())||e.getInventory().getName().equals(CODSetupInventory.setupCodSpawnsInv().getName())) {
					if(e.getCurrentItem()!=null) {
						e.setCancelled(true);

						if(e.getInventory().getName().equals(CODSetupInventory.setupCodInv().getName())) {
							if(e.getSlot()==0) {
								p.openInventory(CODSetupInventory.setupCodSpawnsInv());
							}
							if(e.getSlot()==4) {
								p.openInventory(CODSetupInventory.setupCodGameTypeInv());
							}
							if(e.getSlot()==8) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, p.getLocation(), area.type));
							}
						}

						if(e.getInventory().getName().equals(CODSetupInventory.setupCodGameTypeInv().getName())) {
							if(e.getSlot()==0) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, CODGameType.TEAMS));
							}
							if(e.getSlot()==4) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, CODGameType.INFECTED));
							}
							if(e.getSlot()==8) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, CODGameType.FFA));
							}
						}

						if(e.getInventory().getName().equals(CODSetupInventory.setupCodSpawnsInv().getName())) {
							if(e.getSlot()==0) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								area.teamSpawns.put("RED", new CODSpawn("RED", p.getLocation()));
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, area.type));
							}
							if(e.getSlot()==1) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								area.teamSpawns.put("GREEN", new CODSpawn("GREEN", p.getLocation()));
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, area.type));
							}
							if(e.getSlot()==2) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								area.teamSpawns.put("BLUE", new CODSpawn("BLUE", p.getLocation()));
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, area.type));
							}
							if(e.getSlot()==4) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								area.teamSpawns.put("UNINFECTED", new CODSpawn("UNINFECTED", p.getLocation()));
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, area.type));
							}
							if(e.getSlot()==5) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								area.teamSpawns.put("INFECTED", new CODSpawn("INFECTED", p.getLocation()));
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, area.type));
							}
							if(e.getSlot()==7) {
								Area area = CODUtils.getAreaPlayerIsIn(p);
								area.ffaSpawns.add(new CODSpawn("FFA", p.getLocation()));
								CODUtils.areas.put(area.name, new Area(area.name, area.teamSpawns, area.ffaSpawns, area.lobby, area.type));
							}
						}
					}
				}
			}
		}
	}

}
