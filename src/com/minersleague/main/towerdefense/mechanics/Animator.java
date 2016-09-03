package com.minersleague.main.towerdefense.mechanics;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

import com.minersleague.main.towerdefense.tower.TowerBlock;
import com.minersleague.main.towerdefense.tower.TowerStage;

public class Animator implements Runnable {

	private ArrayList<TowerStage> stages;
	public boolean running;
	private boolean started;
	private boolean done;
	private boolean firstStart;
	private int stageAt;
	private int blockAt;
	private Location towerPos;

	public Animator(ArrayList<TowerStage> stages, Location towerPos) {
		this.stages = stages;
		running = true;
		started = false;
		done = false;
		firstStart = true;
		stageAt = 0;
		blockAt = 0;
		this.towerPos = towerPos;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(running) {
			if(firstStart) {
				stageAt++;
				firstStart = false;
			}
			if(!(stageAt+1>stages.size())) {
				TowerStage stage = stages.get(stageAt);
				if(!started) {
					try {
						Thread.sleep(stage.timeToStart);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					started = true;
					done = false;
				}
				ASyncBuilder builder = new ASyncBuilder() {
					@Override
					public void run() {
						while(!done) {
							if(!(blockAt+1>stage.oldBlocks.size())) {
								TowerBlock oldBlock = stage.oldBlocks.get(blockAt);
								TowerBlock replacement = stage.newBlocks.get(blockAt);
								Block worldBlock = towerPos.getWorld().getBlockAt(towerPos.getBlockX()+oldBlock.x, towerPos.getBlockY()+oldBlock.y, towerPos.getBlockZ()+oldBlock.z);
								worldBlock.setType(replacement.getMaterial());
								if(replacement.direction>0) {
									worldBlock.getState().setData(new MaterialData(replacement.getMaterial(), replacement.direction));
									worldBlock.getState().update();
								}
								blockAt++;
								System.out.println("ASyncBuilder "+blockAt+" "+stageAt);
								try {
									Thread.sleep(stage.delay);
								} catch(InterruptedException e) {
									e.printStackTrace();
								}
							} else {
								done = true;
								stageAt++;
								started = false;
							}
						}
					}
				};
				new Thread(builder).start();
			} else {
				stageAt = 0;
			}
			
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
//			if(firstStart) {
//				firstStart = false;
//				stageAt++;
//				System.out.println("Stage "+stageAt);
//			}
//			if(done) {
//				if(!(stageAt+1>stages.size())) {
//					started = false;
//					done = false;
//					stageAt++;
//					System.out.println("Stage "+stageAt);
//					try {
//						Thread.sleep(20);
//					} catch(InterruptedException e) {
//						e.printStackTrace();
//					}
//				} else {
//					stageAt = 0;
//					started = false;
//					done = false;
//					System.out.println("Stage "+stageAt);
//				}
//			}
//
//			final TowerStage stage = stages.get(stageAt);
//			if(!started) {
//				try {
//					Thread.sleep(stage.timeToStart);
//				} catch(InterruptedException e) {
//					e.printStackTrace();
//				}
//				started = true;
//				System.out.println("Stage "+stageAt);
//			} else {
//				ASyncBuilder builder = new ASyncBuilder() {
//					@Override
//					public void run() {
//						while(!done) {
//							if(!(blockAt+1>stage.oldBlocks.size())) {
//								TowerBlock oldBlock = stage.oldBlocks.get(blockAt);
//								TowerBlock replacement = stage.newBlocks.get(blockAt);
//								Block worldBlock = towerPos.getWorld().getBlockAt(towerPos.getBlockX()+oldBlock.x, towerPos.getBlockY()+oldBlock.y, towerPos.getBlockZ()+oldBlock.z);
//								worldBlock.setType(replacement.getMaterial());
//								if(replacement.direction>0) {
//									worldBlock.getState().setData(new MaterialData(replacement.getMaterial(), replacement.direction));
//									worldBlock.getState().update();
//								}
//								blockAt++;
//								System.out.println("ASyncBuilder "+blockAt+" "+stageAt);
//								try {
//									Thread.sleep(stage.delay);
//								} catch(InterruptedException e) {
//									e.printStackTrace();
//								}
//							} else {
//								done = true;
//							}
//						}
//					}
//				};
//				new Thread(builder).start();
//			}
		}
	}

}
