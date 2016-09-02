package com.minersleague.main.towerdefense;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

public class GameStarter implements Runnable {

	HashMap<Zombie, Integer> zombies = new HashMap<Zombie, Integer>();
	public static boolean running;
	private Game game;
	private GameStarter gs;

	public void startGame(Game game) {
		gs = this;
		this.game = game;
		zombies.put((Zombie)game.getStart().getStart().getWorld().spawnEntity(game.getStart().getStart(), EntityType.ZOMBIE), 0);
		for(Zombie zombie : zombies.keySet()) {
			zombie.setTarget(game.getPoints().get(0).getVillager());
		}
		running = true;
		new Thread(gs).start();
	}

	public void endGame() {
		running = false;
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
				if(zx==px&&zz==pz) {
					System.out.println("Zombie reaced point");
					LivingEntity lentity = null;
					for(Entity entity : game.getPoints().get(at+1).getLocation().getWorld().getNearbyEntities(game.getPoints().get(at+1).getLocation(), 2d, 2d, 2d)) {
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
