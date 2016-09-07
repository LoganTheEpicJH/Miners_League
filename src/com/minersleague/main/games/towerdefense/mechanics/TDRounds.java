package com.minersleague.main.games.towerdefense.mechanics;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

import com.minersleague.main.games.towerdefense.AdvZombie;
import com.minersleague.main.games.towerdefense.TDGame;

public class TDRounds {

	private int roundAt;
	private final int maxRound = 5;
	private ArrayList<AdvZombie> zombies;
	boolean done;
	Thread thread;
	public int zombiesForRound;

	public TDRounds() {
		zombies = new ArrayList<AdvZombie>();
		roundAt = 0;
		done = false;
	}

	public void nextRound(TDGame game, LivingEntity lentity) {
		roundAt++;
		if(!(roundAt>maxRound)) {
			done = false;
			zombiesForRound = 10*(int)Math.floor(1.3)*roundAt;
			System.out.println(zombiesForRound);
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(!done) {
						if(zombiesForRound==0) {
							done = true;
							thread.interrupt();
							break;
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
		} else {
			game.won = true;
		}
	}

	public ArrayList<AdvZombie> getZombies() {
		return zombies;
	}

	public int getRoundAt() {
		return roundAt;
	}

}
