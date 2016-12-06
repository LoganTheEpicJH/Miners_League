package com.minersleague.main.games.towerdefense.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.util.Utilities;

public class TDCountdown extends SimpleThread {

	public boolean done;
	private TDGameRunner gs;
	private int countdown;
	private TDCountdown tdc;
	
	public TDCountdown(TDGameRunner gs, int countdown) {
		tdc = this;
		done = false;
		this.gs = gs;
		this.countdown = countdown;
		executeThread(tdc);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			if(countdown==0) {
				done = true;
				gs.startGame();
				cancelThread();
			} else {
				countdown--;
				for(String up : gs.game.getPlayersPlaying()) {
					Player p = Bukkit.getServer().getPlayer(up);
					p.setExp(0.0F);
					p.setLevel(0);
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
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {}
			}
		}
	}
	
}
