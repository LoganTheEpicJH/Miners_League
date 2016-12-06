package com.minersleague.main.games.towerdefense.mechanics;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.tower.TowerBlock;
import com.minersleague.main.games.towerdefense.tower.TowerStage;

public class TDAnimator extends SimpleThread {

	private TowerStage stage;
	private boolean started;
	public boolean done;
	private int blockAt;
	private Location towerPos;
	public String id;
	private TDAnimator animator;

	public TDAnimator(String gameName, TowerStage stage, Location towerPos) {
		this.id = setID(gameName+"-Animator");
		this.stage = stage;
		started = false;
		done = false;
		blockAt = 0;
		this.towerPos = towerPos;
		animator = this;
		executeThread(animator);
		TDUtils.idLink.put(id, null);
		TDUtils.idLink.put(id, animator);
	}

	public void stop() {
		cancelThread();
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
					block.setType(tb.getBlock().getMaterial());
					if(tb.getBlock().getBlockMetaData()>0) {
						block.setData((byte)tb.getBlock().getBlockMetaData(), true);
						block.getState().update();
					}
					blockAt++;
					try {
						Thread.sleep(stage.delay);
					} catch(InterruptedException e) {}
				} else {
					done = true;
				}
			}
		}
	}

}
