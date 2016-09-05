package com.minersleague.main.towerdefense.tower.function;

import java.util.Arrays;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

public class TeslaFunction extends TowerFunction {

	int radius;
	public double distance;
	
	public TeslaFunction(int radius) {
		this.radius = radius;
		activeAtStage = 0;
	}

	@Override
	public void run() {
		while(repeating) {
			System.out.println("Running");
			Entity entity = (Entity)Arrays.asList(towerPos.getWorld().getNearbyEntities(towerPos, radius, radius, radius).toArray()).get(0);
			if(entity!=null) {
				((Zombie)entity).damage(0.5D);
			} else {
				System.out.println("No Entity");
			}
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void start() {}
	

}
