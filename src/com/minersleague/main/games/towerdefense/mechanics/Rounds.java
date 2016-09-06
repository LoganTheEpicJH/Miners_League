package com.minersleague.main.games.towerdefense.mechanics;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

import com.minersleague.main.games.towerdefense.AdvZombie;
import com.minersleague.main.games.towerdefense.Game;

public class Rounds {

	private int roundAt;
	// private final int maxRound = 5;
	public boolean completed;
	private ArrayList<AdvZombie> zombies;
	boolean done;
	Thread thread;
	int zombiesForRound;

	public Rounds() {
		zombies = new ArrayList<AdvZombie>();
		completed = false;
		roundAt = 0;
		done = false;
	}

	public void nextRound(Game game, LivingEntity lentity) {
		roundAt++;
		done = false;
		completed = false;
		zombiesForRound = 10*(int)Math.floor(1.3)*roundAt;
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!done) {
					if(zombiesForRound==0) {
						done = true;
						completed = true;
						thread.interrupt();
					} else {
						zombiesForRound--;
						AdvZombie adv = new AdvZombie("Z-"+game.getName(), game.getStart());
						adv.getSpawn().setTarget(lentity);
						zombies.add(adv);
					}
					try {
						Thread.sleep(1000);
					} catch(InterruptedException e) {}
				}
			}
		});
		thread.start();
	}

	public ArrayList<AdvZombie> getZombies() {
		return zombies;
	}

	public int getRoundAt() {
		return roundAt;
	}

}
