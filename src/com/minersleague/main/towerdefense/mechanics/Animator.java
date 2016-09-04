package com.minersleague.main.towerdefense.mechanics;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.minersleague.main.towerdefense.BlockMetaData;
import com.minersleague.main.towerdefense.IDAble;
import com.minersleague.main.towerdefense.tower.TowerBlock;
import com.minersleague.main.towerdefense.tower.TowerStage;
import com.minersleague.main.util.Utilities;

public class Animator extends IDAble implements Runnable {

	private TowerStage stage;
	private boolean started;
	public boolean done;
	private int blockAt;
	private Location towerPos;
	public String id;
	private Animator animator;

	public Animator(String gameName, int animatorIndex, TowerStage stage, Location towerPos) {
		this.id = setID(gameName+"-Animator"+animatorIndex);
		this.stage = stage;
		started = false;
		done = false;
		blockAt = 0;
		this.towerPos = towerPos;
		animator = this;
		Utilities.idLink.put(id, animator);
	}

	public void stop() {
		done = true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			if(!started) {
				try {
					Thread.sleep(stage.timeToStart);
				} catch(InterruptedException e) {}
				started = true;
			} else {
				if(blockAt<=stage.blocksToPlace.size()-1) {
					TowerBlock tb = stage.blocksToPlace.get(blockAt);
					Block block = towerPos.getWorld().getBlockAt(towerPos.getBlockX()+tb.x, towerPos.getBlockY()+tb.y, towerPos.getBlockZ()+tb.z); 
					block.setType(tb.getMaterial());
					if(tb.getMetaData()!=null) {
						for(BlockMetaData meta : tb.getMetaData()) {
							block.setData((byte)meta.getMeta(), true);
							block.getState().update();
						}
					} else if(tb.getSpecialData()>0) {
						block.setData((byte)tb.getSpecialData(), true);
						block.getState().update();
					}
					blockAt++;
					try {
						Thread.sleep(stage.delay);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					done = true;
				}
			}
		}
	}

}
