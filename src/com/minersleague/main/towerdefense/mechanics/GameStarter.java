package com.minersleague.main.towerdefense.mechanics;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

import com.minersleague.main.Main;
import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.util.Utilities;

public class GameStarter implements Runnable {

	public static boolean running;
	private Game game;
	private GameStarter gs;
	private Countdown c;
	private Rounds round;
	public ArrayList<UUID> playing;
	private LivingEntity lentity;
	
	public void initGameStart(Game game) {
		lentity = null;
		if(!Utilities.running.containsKey(game.getName())) {
			Utilities.running.put(game.getName(), false);
		}
		if(!Utilities.running.get(game.getName())) {
			this.game = game;
			gs = this;
			c = new Countdown(gs);
			playing = new ArrayList<UUID>();
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				if(Utilities.playing.keySet().contains(p.getUniqueId())) {
					switch(Utilities.playing.get(p.getUniqueId())) {
						case PLAYING:
							break;
						case NOT_PLAYING:
							break;
						case IN_LOBBY:
							p.teleport(game.getPlayground());
							p.setGameMode(GameMode.CREATIVE);
							playing.add(p.getUniqueId());
							break;
						default:
							break;
					}
				}
			}
			startCountdown();
		}
	}

	public void startCountdown() {
		running = true;
		if(!playing.isEmpty()) {
			new Thread(c).start();
		}
	}

	public void startGame() {
		round = new Rounds();
		round.nextRound(game);
		new Thread(gs).start();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
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
				for(Zombie zombie : round.getZombies()) {
					zombie.setTarget(lentity);
				}
				Utilities.running.put(game.getName(), true);
			}
		}, 10);
	}

	public void endGame() {
		running = false;
		for(Entity zombie : game.getPlayground().getWorld().getEntities()) {
			if(zombie instanceof Zombie&&zombie.getCustomName()!=null) {
				if(zombie.getCustomName().equals("Z-"+game.getName())) {
					((Damageable)zombie).setHealth(0.0D);
				}
			}
		}
		for(UUID up : playing) {
			Utilities.playing.put(up, PlayingStage.NOT_PLAYING);
		}
	}

	@Override
	public void run() {
		while(running) {
			for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(game.getEnd(), 2D, 2D, 2D)) {
				if(entity instanceof Zombie) {
					((Zombie)entity).setHealth(0.0D);
				}
			}
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
