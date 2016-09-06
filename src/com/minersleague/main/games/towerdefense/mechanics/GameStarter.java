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

import com.minersleague.main.games.towerdefense.AdvZombie;
import com.minersleague.main.games.towerdefense.Game;
import com.minersleague.main.games.towerdefense.IDAble;
import com.minersleague.main.games.towerdefense.PlayingStage;
import com.minersleague.main.games.towerdefense.TowerDefensePlayerStorage;
import com.minersleague.main.util.Utilities;

public class GameStarter extends IDAble implements Runnable {

	public static boolean running;
	public boolean allowed;
	public Game game;
	private GameStarter gs;
	public Countdown c;
	private Rounds round;
	private LivingEntity lentity;
	public String id;
	public Thread thread;

	public GameStarter(Game game) {
		AsyncCatcher.enabled = false;
		id = setID(game.getName()+"-GameStarter");
		gs = this;
		this.game = game;
		c = new Countdown(gs, 61);
		allowed = false;
		running = true;
		Utilities.idLink.put(id, gs);
	}
	
	public void initGameStart() {
		lentity = null;
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
				Utilities.gameIn.put(p.getName(), new TowerDefensePlayerStorage(game.getName(), PlayingStage.PLAYING));
			}
		}
	}

	public void startCountdown() {
		while(!game.getPlayersPlaying().isEmpty()) {}
		new Thread(c).start();
	}

	public void startGame() {
		initGameStart();
		round = new Rounds();
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

	public void endGame() {
		running = false;
		for(String s : Utilities.gameIn.keySet()) {
			if(Utilities.gameIn.get(s)!=null) {
				if(Utilities.gameIn.get(s).equals(game.getName())) {
					Utilities.gameIn.put(s, null);
					Utilities.gameIn.put(s, new TowerDefensePlayerStorage(game.getName(), PlayingStage.IN_LOBBY));
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
			Utilities.gameIn.put(up, null);
		}
		thread.interrupt();
	}

	@Override
	public void run() {
		while(running) {
			if(round.completed) {
				round.nextRound(game, lentity);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {}
			}
			if(allowed) {
				for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(new Location(game.getEnd().getWorld(), game.getEnd().getX(), game.getEnd().getBlockY()+2, game.getEnd().getZ()), 2d, 2d, 2d)) {
					if(entity instanceof Zombie) {
						Zombie zombie = (Zombie)entity;
						// System.out.println("Found Zombie zx: "+zombie.getLocation().getBlockX()+" zz: "+zombie.getLocation().getBlockY()+" | End x: "+game.getEnd().getBlockX()+" z: "+game.getEnd().getBlockZ());
						if(zombie.getLocation().getBlockX()==game.getEnd().getBlockX()&&zombie.getLocation().getBlockZ()==game.getEnd().getBlockZ()) {
							// System.out.println("Found Zombie");
							zombie.setHealth(0.0D);
							game.zombiePassed();
							if(game.lost) {
								Utilities.stopAllGameActions(game);
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
