package com.minersleague.main.games.towerdefense.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;
import org.spigotmc.AsyncCatcher;

import com.minersleague.main.games.generall.PlayerStorage;
import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.AdvZombie;
import com.minersleague.main.games.towerdefense.TDGame;
import com.minersleague.main.util.Utilities;

public class TDGameRunner extends SimpleThread {

	public static boolean running;
	public boolean allowed;
	public TDGame game;
	private TDGameRunner gs;
	public TDCountdown c;
	private TDRounds round;
	private LivingEntity lentity;
	public String id;

	public TDGameRunner(TDGame game) {
		AsyncCatcher.enabled = false;
		id = setID(game.getName()+"-GameStarter");
		gs = this;
		this.game = game;
		allowed = false;
		running = true;
		TDUtils.idLink.put(id, gs);
		startCountdown();
	}

	public void startCountdown() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(game.getPlayersPlaying().contains(p.getName())) {
				p.teleport(game.getPlayground());
				p.setGameMode(GameMode.CREATIVE);
				TDUtils.gameIn.put(p.getName(), new PlayerStorage(game.getName(), PlayingStage.PLAYING));
			}
		}
		c = new TDCountdown(gs, 31);
	}

	public void startGame() {
		initGameStart();
		round = new TDRounds();
		for(Entity entity : game.getEnd().getWorld().getNearbyEntities(game.getEnd(), 2d, 2d, 2d)) {
			if(entity instanceof Villager) {
				Villager v = (Villager)entity;
				if(v.getCustomName().equals("End-"+game.getName())) {
					lentity = (LivingEntity)entity;
					break;
				}
			}
		}
		round.nextRound(game, lentity);
		allowed = true;
	}
	
	public void initGameStart() {
		lentity = null;
		executeThread(gs);
		boolean foundVillager = false;
		if(!game.getPlayground().getWorld().getNearbyEntities(game.getEnd(), 2D, 2D, 2D).isEmpty()) {
			for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(game.getEnd(), 2D, 2D, 2D)) {
				if(entity instanceof Villager) {
					foundVillager = true;
					break;
				}
			}
		}
		if(!foundVillager) {
			Villager villager = (Villager)game.getPlayground().getWorld().spawnEntity(game.getEnd(), EntityType.VILLAGER);
			villager.setAdult();
			villager.setProfession(Profession.FARMER);
			villager.setSilent(true);
			villager.setCustomName("End-"+game.getName());
			villager.setCustomNameVisible(true);
			villager.setAI(false);
			foundVillager = true;
		}
	}
	
	public void endGame() {
		running = false;
		for(String s : TDUtils.gameIn.keySet()) {
			if(TDUtils.gameIn.get(s)!=null) {
				if(TDUtils.gameIn.get(s).equals(game.getName())) {
					TDUtils.gameIn.put(s, null);
					TDUtils.gameIn.put(s, new PlayerStorage(game.getName(), PlayingStage.IN_LOBBY));
					Bukkit.getServer().getPlayer(s).teleport(game.getLobby());
				}
			}
		}
		for(Entity zombie : game.getPlayground().getWorld().getEntities()) {
			if(zombie instanceof Zombie&&zombie.getCustomName()!=null) {
				if(zombie.getCustomName().equals("Z-"+game.getName())) {
					((Damageable)zombie).setHealth(0.0D);
				}
			}
		}
		for(String up : game.getPlayersPlaying()) {
			TDUtils.gameIn.put(up, null);
		}
		cancelThread();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(running) {
			if(allowed) {
				if(game.getZombiesKilled()>=round.zombiesForRound) {
					round.nextRound(game, lentity);
					try {
						Thread.sleep(1000);
					} catch(InterruptedException e) {}
				}
				for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(new Location(game.getEnd().getWorld(), game.getEnd().getX(), game.getEnd().getBlockY()+2, game.getEnd().getZ()), 2d, 3d, 2d)) {
					if(entity instanceof Zombie) {
						Zombie zombie = (Zombie)entity;
						// System.out.println("Found Zombie zx: "+zombie.getLocation().getBlockX()+" zz: "+zombie.getLocation().getBlockY()+" | End x: "+game.getEnd().getBlockX()+" z: "+game.getEnd().getBlockZ());
						if(zombie.getLocation().getBlockX()==game.getEnd().getBlockX()&&zombie.getLocation().getBlockZ()==game.getEnd().getBlockZ()) {
							// System.out.println("Found Zombie");
							zombie.setHealth(0.0D);
							game.zombiePassed();
							if(game.lost) {
								TDUtils.stopAllGameActions(game);
							}
							if(game.won) {
								TDUtils.stopAllGameActions(game);
								for(String s : TDUtils.gameIn.keySet()) {
									if(TDUtils.gameIn.get(s).getGameName().equals(game.getName())) {
										TDUtils.gameIn.put(s, new PlayerStorage(s, PlayingStage.NONE));
										Bukkit.getServer().getPlayer(s).sendTitle(Utilities.color("&a&lGAME WON"), Utilities.color("&c&lYou cleared all Rounds!"));
									}
								}
							}
						}
					}
				}
				for(AdvZombie zombie : round.getZombies()) {
					zombie.getSpawn().setTarget(lentity);
				}
			}
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {}
		}
	}

}
