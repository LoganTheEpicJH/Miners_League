package com.minersleague.main.towerdefense.mechanics;

import com.minersleague.main.towerdefense.IDAble;
import com.minersleague.main.towerdefense.tower.TowerBuilder;
import com.minersleague.main.towerdefense.tower.TowerStage;
import com.minersleague.main.util.Utilities;

public class Animation extends IDAble implements Runnable {

	private TowerBuilder towerBuilder;
	public boolean animate;
	private Animator animator;
	public Thread ownThread;
	private Thread thread;
	private int done;
	public String id;
	private String gameName;
	private Animation animation;
	private int animatorAt;
	
	public Animation(String gameName, TowerBuilder towerBuilder) {
		this.gameName = gameName;
		this.id = setID(gameName+"-Animation");
		this.towerBuilder = towerBuilder;
		animate = true;
		done = 0;
		animatorAt = 0;
		animation = this;
		nextStage();
		ownThread = new Thread(animation);
		ownThread.start();
		Utilities.idLink.put(id, animation);
	}
	
	public void stop() {
		animate = false;
		if(ownThread.isAlive()) {
			ownThread.interrupt();
		}
		if(thread.isAlive()&&thread!=null) {
			thread.interrupt();
		}
		if(animator!=null) {
			animator.done = true;
		}
	}
	
	public void nextStage() {
		if(done==towerBuilder.tower.getTowerStages().size()) {
			
			done = 0;
		}
		TowerStage stage = towerBuilder.tower.getTowerStages().get(done);
		if(stage!=null) {
			animatorAt++;
			//Location loc = new Location(towerBuilder.location.getWorld(), towerBuilder.location.getBlockX(), towerBuilder.location.getBlockY()+2.5, towerBuilder.location.getBlockZ()); //towerBuilder.location;
			animator = new Animator(gameName, animatorAt, stage, towerBuilder.location);
			thread = new Thread(animator);
			thread.start();
			done++;
		}
	}
	
	@Override
	public void run() {
		while(animate) {
			if(animator.done) {
				thread.interrupt();
				animator = null;
				thread = null;
				nextStage();
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
		}
	}
	
}
