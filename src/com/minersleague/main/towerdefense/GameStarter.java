package com.minersleague.main.towerdefense;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

import com.minersleague.main.Main;
import com.minersleague.main.util.Utilities;

public class GameStarter implements Runnable {

	// HashMap<String, Zombie> zombies;
	// HashMap<String, Integer> zombieAt;
	public static boolean running;
	private Game game;
	private GameStarter gs;
	private Rounds round;

	public void startGame(Game game) {
		if(!Utilities.running.containsKey(game)) {
			Utilities.running.put(game, false);
		}
		if(!Utilities.running.get(game)) {
			gs = this;
			// zombies = new HashMap<String, Zombie>();
			// zombieAt = new HashMap<String, Integer>();
			this.game = game;
			round = new Rounds();
			round.nextRound(game);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
				@Override
				public void run() {
					LivingEntity lentity = null;
					for(Entity entity : game.getPoints().get(0).getLocation().getWorld().getNearbyEntities(game.getPoints().get(0).getLocation(), 1d, 2d, 1d)) {
						if(entity instanceof Villager) {
							lentity = (LivingEntity)entity;
							break;
						}
					}
					for(Zombie zombie : round.getZombies()) {
						zombie.setTarget(lentity);
					}
					running = true;
					Utilities.running.put(game, true);
					new Thread(gs).start();
				}
			}, 10);
		}
	}

	public void endGame() {
		running = false;
//		for(Entity entity : game.getStart().getLocation().getWorld().getEntities()) {
//			if(entity instanceof Zombie) {
//				((Zombie)entity).setHealth(0.0D);
//			}
//		}
	}

	@Override
	public void run() {
		while(running) {
			for(Zombie zombie : round.getZombies()) {
				int zx = zombie.getLocation().getBlockX();
				int zz = zombie.getLocation().getBlockZ();
				int px = game.getEnd().getLocation().getBlockX();
				int pz = game.getEnd().getLocation().getBlockZ();
				if(zx==px&&zz==pz) {
					zombie.setHealth(0.0D);
				}
			}
			for(int z = 0; z<round.getZombies().size(); z++) {
				for(int i = 0; i<game.getPoints().size(); i++) {
					Zombie zombie = round.getZombies().get(z);
					Point point = game.getPoints().get(i);
					int zx = zombie.getLocation().getBlockX();
					int zz = zombie.getLocation().getBlockZ();
					int px = point.getLocation().getBlockX();
					int pz = point.getLocation().getBlockZ();
					if(zx==px&&zz==pz) {
						LivingEntity lentity = null;
						if((i+1)<game.getPoints().size()&&game.getPoints().get(i).getID()>=1) {
							for(Entity entity : game.getPoints().get(i+1).getLocation().getWorld().getNearbyEntities(game.getPoints().get(i+1).getLocation(), 1d, 2d, 1d)) {
								if(entity instanceof Villager) {
									lentity = (LivingEntity)entity;
									break;
								}
							}
							zombie.setTarget(lentity);
						}
					}
				}
			}
//			for(Point p : game.getPoints()) {
//				for(Entity entity : p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 1d, 5d, 1d)) {
//					if(entity instanceof Zombie) {
//						Zombie zombie = (Zombie)entity;
//						LivingEntity lentity = null;
//						int at = zombies.get(zombie);
//						for(Entity entity2 : game.getPoints().get(at+1).getLocation().getWorld().getNearbyEntities(game.getPoints().get(at+1).getLocation(), 1d, 2d, 1d)) {
//							if(entity2 instanceof Villager) {
//								lentity = (LivingEntity)entity;
//								break;
//							}
//						}
//						zombie.setTarget(lentity);
//						zombies.put(zombie, at+1);
//						break;
//					}
//				}
//			}
//			for(String zombieString : zombies.keySet()) {
//				Zombie zombie = zombies.get(zombieString);
//				int at = zombieAt.get(zombieString);
//				Point point = game.getPoints().get(at);
//				int zx = zombie.getLocation().getBlockX();
//				int zz = zombie.getLocation().getBlockZ();
//				int px = point.getLocation().getBlockX();
//				int pz = point.getLocation().getBlockZ();
//				if(zx==px&&zz==pz&&point.getID()>1) {
//					if(zx==game.getEnd().getLocation().getBlockX()&&zz==game.getEnd().getLocation().getBlockZ()) {
//						zombie.remove();
//						zombies.remove(zombie);
//					}
//					// System.out.println("Zombie reaced point");
//					LivingEntity lentity = null;
//					for(Entity entity : game.getPoints().get(at+1).getLocation().getWorld().getNearbyEntities(game.getPoints().get(at+1).getLocation(), 1d, 2d, 1d)) {
//						if(entity instanceof Villager) {
//							lentity = (LivingEntity)entity;
//							break;
//						}
//					}
//					zombie.setTarget(lentity);
//					zombieAt.put(zombieString, at+1);
//				}
//			}
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
