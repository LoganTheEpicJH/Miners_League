package com.minersleague.main.games.codwarfare;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.CODUtils;
import com.minersleague.main.util.Utilities;

public class CODCountdown extends SimpleThread {

	public boolean done;
	private int countdow;

	public CODCountdown(int countdown) {
		done = false;
		this.countdow = countdown;
		executeThread(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				if(CODUtils.gameIn.keySet().contains(p.getName())) {
					p.setExp(0f);
					p.setLevel(countdow);
					if(countdow==0) {
						done = true;
						break;
					}
					if(countdow==30) {
						p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l30 Seconds left"));
					}
					if(countdow==20) {
						p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&20 Seconds left"));
					}
					if(countdow<=10) {
						if(countdow!=1) {
							p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l"+countdow+" Seconds left"));
						} else {
							p.sendTitle(Utilities.color("&c&lCountdown"), Utilities.color("&a&l"+countdow+" Second left"));
						}
						p.resetTitle();
					}
					countdow--;
				}
			}
			if(done) {
				cancelThread();
				break;
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
		}
	}

}
