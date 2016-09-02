package com.minersleague.main.towerdefense;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

public class TowerBuilder implements Runnable {

	private int x, y, z;
	private Location location;
	private Tower tower;
	public boolean done;
	private int at;
	private long delay;

	public TowerBuilder(Location location, Tower tower, long delay) {
		this.location = location;
		this.tower = tower;
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
		at = 0;
		this.delay = delay;
		done = false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			if(!(at+1>tower.getBlocks().size())) {
				//System.out.println("Done: "+done+" At+1: "+(at+1)+" At: "+at+" Size: "+tower.getBlocks().size());
				if(tower.getBlocks().get(at)!=null) {
					TowerBlock block = tower.getBlocks().get(at);
					Block worldBlock = location.getWorld().getBlockAt(x+block.x, y+block.y, z+block.z);
					worldBlock.setType(block.getMaterial());
					if(block.direction>0) {
						worldBlock.getState().setData(new MaterialData(block.getMaterial(), block.direction));
					}
					worldBlock.getState().update();
					done = false;
					at++;
					try {
						Thread.sleep(delay);
					} catch(InterruptedException e) {
						System.out.println("TowerBuilder got an Error. Line 44");
					}
				} else {
					done = true;
					//System.out.println("Interruption 1 | A Block is Null TowerBuilder:50");
				}
			} else {
				done = true;
				//System.out.println("Interruption 2 | Done");
			}
		}
	}

}
