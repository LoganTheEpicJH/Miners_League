package com.minersleague.main.towerdefense.tower;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.minersleague.main.towerdefense.BlockMetaData;
import com.minersleague.main.towerdefense.IDAble;
import com.minersleague.main.towerdefense.mechanics.Animation;
import com.minersleague.main.util.Utilities;

public class TowerBuilder extends IDAble implements Runnable {

	private int x, y, z;
	public Location location;
	public Tower tower;
	public boolean done;
	private int at;
	private long delay;
	private TowerBuilder towerBuilder;
	public String id;
	private String gameName;
	public Thread thread;

	public TowerBuilder(String gameName, Location location, Tower tower, long delay) {
		this.gameName = gameName;
		id = setID(gameName+"-TowerBuilder");
		//System.out.println(id);
		this.location = location;
		this.tower = tower;
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
		at = 0;
		this.delay = delay;
		done = false;
		towerBuilder = this;
		thread = new Thread(towerBuilder);
		thread.start();
		Utilities.idLink.put(id, towerBuilder);
	}

	public void interrupt() {
		if(thread.isAlive()) {
			thread.interrupt();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			if(!(at+1>tower.getBlocks().size())) {
				// System.out.println("Done: "+done+" At+1: "+(at+1)+" At: "+at+" Size: "+tower.getBlocks().size());
				if(tower.getBlocks().get(at)!=null) {
					TowerBlock block = tower.getBlocks().get(at);
					Block worldBlock = location.getWorld().getBlockAt(x+block.x, y+block.y, z+block.z);
					worldBlock.setType(block.getMaterial());
					if(block.getMetaData()!=null) {
						for(BlockMetaData meta : block.getMetaData()) {
							worldBlock.setData((byte)meta.getMeta(), true);
							worldBlock.getState().update();
						}
					} else if(block.getSpecialData()>0) {
						worldBlock.setData((byte)block.getSpecialData(), true);
						worldBlock.getState().update();
					}
					done = false;
					at++;
					try {
						Thread.sleep(delay);
					} catch(InterruptedException e) {}
				} else {
					done = true;
					// System.out.println("Interruption 1 | A Block is Null TowerBuilder:50");
				}
			} else {
				if(tower.hasStages()) {
					new Animation(gameName, towerBuilder);
				}
				done = true;
				// System.out.println("Interruption 2 | Done");
			}
		}
	}

}
