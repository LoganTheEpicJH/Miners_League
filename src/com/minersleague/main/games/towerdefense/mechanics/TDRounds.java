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
	public int zombiesForRoundSpawn;
	public TDGameRunner gs;

	public TDRounds(TDGameRunner gs) {
		zombies = new ArrayList<AdvZombie>();
		roundAt = 0;
		done = false;
		this.gs = gs;
	}

	public void nextRound(TDGame game, LivingEntity lentity) {
		roundAt++;
		if(!(roundAt>maxRound)) {
			done = false;
			zombiesForRound = 10*(int)Math.floor(1.3)*roundAt;
			zombiesForRoundSpawn = zombiesForRound;
			System.out.println(zombiesForRound);
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(!done) {
						if(zombiesForRoundSpawn==0) {
							done = true;
							if(thread.isAlive()) {
								thread.interrupt();
							}
							break;
						} else {
							zombiesForRoundSpawn--;
							AdvZombie adv = new AdvZombie("Z-"+game.getName(), game.getStart());
							//adv.getSpawn().setTarget(lentity);
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
	}

	public ArrayList<AdvZombie> getZombies() {
		return zombies;
	}

	public int getRoundAt() {
		return roundAt;
	}

}
