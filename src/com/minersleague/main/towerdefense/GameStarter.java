package com.minersleague.main.towerdefense;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class GameStarter implements Runnable {

	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static boolean running;
	private Game game;
	
	public void startGame(Game game) {
		this.game = game;
		zombies.add((Zombie)game.getStart().getStart().getWorld().spawnEntity(game.getStart().getStart(), EntityType.ZOMBIE));
		for(Zombie zombie : zombies) {
			zombie.setTarget(game.getPoints().get(0).getVillager());
		}
		running = true;
		new Thread(new GameStarter()).start();
	}

	public void endGame() {
		running = false;
	}
	
	@Override
	public void run() {
		while(running) {
			for(Zombie zombie : zombies) {
				for(Point point : game.getPoints()) {
					int zx = zombie.getLocation().getBlockX();
					int zz = zombie.getLocation().getBlockZ();
					int px = point.getLocation().getBlockX();
					int pz = point.getLocation().getBlockZ();
					if(zx==px&&zz==pz) {
						zombie.setTarget(game.getPoints().get(point.getID()).getVillager());
					}
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
