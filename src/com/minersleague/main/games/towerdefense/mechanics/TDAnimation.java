package com.minersleague.main.games.towerdefense.mechanics;

import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.tower.TowerBuilder;
import com.minersleague.main.games.towerdefense.tower.TowerStage;

public class TDAnimation extends SimpleThread {

	private TowerBuilder towerBuilder;
	public boolean animate;
	private TDAnimator animator;
	private int done;
	public String id;
	private String gameName;
	private TDAnimation animation;
	
	public TDAnimation(String gameName, TowerBuilder towerBuilder) {
		this.gameName = gameName;
		this.id = setID(gameName+"-Animation");
		this.towerBuilder = towerBuilder;
		animate = true;
		done = 0;
		animation = this;
		nextStage();
		executeThread(animation);
		TDUtils.idLink.put(id, animation);
	}
	
	public void stop() {
		animate = false;
		cancelThread();
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
			animator = new TDAnimator(gameName, stage, towerBuilder.location);
			done++;
		}
	}
	
	@Override
	public void run() {
		while(animate) {
			if(animator.done) {
				animator.stop();
				animator = null;
				nextStage();
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
		}
	}
	
}
