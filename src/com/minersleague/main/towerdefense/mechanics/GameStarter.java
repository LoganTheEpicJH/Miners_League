package com.minersleague.main.towerdefense.mechanics;

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
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.AsyncCatcher;

import com.minersleague.main.Main;
import com.minersleague.main.towerdefense.AdvZombie;
import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.towerdefense.IDAble;
import com.minersleague.main.towerdefense.PlayingStage;
import com.minersleague.main.util.Utilities;

public class GameStarter extends IDAble implements Runnable {

	public static boolean running;
	public boolean allowed;
	public Game game;
	private GameStarter gs;
	private Countdown c;
	private Rounds round;
	private LivingEntity lentity;
	public String id;
	public Thread thread;

	public void initGameStart(Game game) {
		id = setID(game.getName()+"-GameStarter");
		allowed = false;
		running = true;
		this.game = game;
		AsyncCatcher.enabled = false;
		gs = this;
		lentity = null;
		c = new Countdown(gs);
		thread = new Thread(gs);
		thread.start();
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
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(game.getPlayersPlaying().contains(p.getName())) {
				p.teleport(game.getPlayground());
				p.setGameMode(GameMode.CREATIVE);
				Utilities.playingStage.put(p.getName(), PlayingStage.PLAYING);
			}
		}

		startCountdown();
	}

	public void startCountdown() {
		if(!game.getPlayersPlaying().isEmpty()) {
			new Thread(c).start();
		}
	}

	public void startGame() {
		round = new Rounds();
		round.nextRound(game);
		new BukkitRunnable() {
			@Override
			public void run() {
				for(Entity entity : game.getEnd().getWorld().getNearbyEntities(game.getEnd(), 2d, 2d, 2d)) {
					if(entity instanceof Villager) {
						Villager v = (Villager)entity;
						if(v.getCustomName().equals("End-"+game.getName())) {
							lentity = (LivingEntity)entity;
							break;
						}
					}
				}
				for(AdvZombie zombie : round.getZombies()) {
					zombie.getSpawn().setTarget(lentity);
				}
			}
		}.runTaskTimer(Main.plugin, 10, 160);
		allowed = true;
		Utilities.idLink.put(id, gs);
	}

	public void endGame() {
		running = false;
		for(String s : Utilities.gameIn.keySet()) {
			if(Utilities.gameIn.get(s)!=null) {
				if(Utilities.gameIn.get(s).equals(game.getName())) {
					Utilities.gameIn.put(s, null);
					Utilities.playingStage.put(s, PlayingStage.NONE);
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
			Utilities.gameIn.put(up, null);
		}
		thread.interrupt();
	}

	@Override
	public void run() {
		while(running) {
			if(allowed) {
				for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(new Location(game.getEnd().getWorld(), game.getEnd().getX(), game.getEnd().getBlockY()+2, game.getEnd().getZ()), 2d, 2d, 2d)) {
					if(entity instanceof Zombie) {
						Zombie zombie = (Zombie)entity;
						// System.out.println("Found Zombie zx: "+zombie.getLocation().getBlockX()+" zz: "+zombie.getLocation().getBlockY()+" | End x: "+game.getEnd().getBlockX()+" z: "+game.getEnd().getBlockZ());
						if(zombie.getLocation().getBlockX()==game.getEnd().getBlockX()&&zombie.getLocation().getBlockZ()==game.getEnd().getBlockZ()) {
							// System.out.println("Found Zombie");
							zombie.setHealth(0.0D);
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
