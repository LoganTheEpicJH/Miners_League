package com.minersleague.main.towerdefense.mechanics;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.minersleague.main.util.Utilities;

public class Countdown implements Runnable {

	public boolean done;
	private GameStarter gs;
	private int countdown;

	public Countdown(GameStarter gs) {
		done = false;
		this.gs = gs;
		countdown = 31;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			if(countdown==0) {
				done = true;
			} else {
				countdown--;
				for(UUID up : gs.playing) {
					Player p = Bukkit.getServer().getPlayer(up);
					p.setExp(0.0F);
					p.setLevel(countdown);
					if(countdown==30) {
						p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l30 Seconds remaining!"));
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
					}
					if(countdown==20) {
						p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l20 Seconds remaining!"));
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
					}
					if(countdown==10) {
						p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l10 Seconds remaining!"));
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
					}
					if(countdown<10) {
						p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l"+countdown+" Seconds remaining!"));
						p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
						p.resetTitle();
					}
				}
				System.out.println(countdown);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
