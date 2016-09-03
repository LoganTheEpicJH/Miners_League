package com.minersleague.main.towerdefense;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

import com.minersleague.main.Main;

public class GameStarter implements Runnable {

	HashMap<Zombie, Integer> zombies;
	public static boolean running;
	private Game game;
	private GameStarter gs;
	private Rounds round;

	public void startGame(Game game) {
		if(!Games.running.get(game)) {
			gs = this;
			zombies = new HashMap<Zombie, Integer>();
			this.game = game;
			round = new Rounds();
			for(Zombie zombie : round.getZombies()) {
				zombies.put(zombie, 0);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					for(Zombie zombie : zombies.keySet()) {
						zombie.setTarget(game.getPoints().get(0).getVillager());
					}
					running = true;
					Games.running.put(game, true);
					new Thread(gs).start();
				}
			}, 10);
		}
	}

	public void endGame() {
		running = false;
		for(Zombie zombie : round.getZombies()) {
			zombie.remove();
		}
		zombies.clear();
		Games.running.put(game, false);
	}

	@Override
	public void run() {
		while(running) {
			for(Zombie zombie : zombies.keySet()) {
				int at = zombies.get(zombie);
				Point point = game.getPoints().get(at);
				int zx = zombie.getLocation().getBlockX();
				int zz = zombie.getLocation().getBlockZ();
				int px = point.getLocation().getBlockX();
				int pz = point.getLocation().getBlockZ();
				if(zx==px&&zz==pz&&point.getID()>1) {
					if(zx==game.getEnd().getEnd().getBlockX()&&zz==game.getEnd().getEnd().getBlockZ()) {
						zombie.remove();
						zombies.remove(zombie);
					}
					// System.out.println("Zombie reaced point");
					LivingEntity lentity = null;
					for(Entity entity : game.getPoints().get(at+1).getLocation().getWorld().getNearbyEntities(game.getPoints().get(at+1).getLocation(), 1d, 2d, 1d)) {
						if(entity instanceof Villager) {
							lentity = (LivingEntity)entity;
							break;
						}
					}
					zombie.setTarget(lentity);
					zombies.put(zombie, at+1);
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
