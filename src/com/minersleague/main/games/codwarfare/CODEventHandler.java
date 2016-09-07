package com.minersleague.main.games.codwarfare;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.util.CODUtils;

public class CODEventHandler implements Listener {

	@EventHandler
	public void onGunShoot(PlayerInteractEvent e) {
		if(CODUtils.gameIn.get(e.getPlayer().getName())!=null) {
			if(CODUtils.gameIn.get(e.getPlayer().getName()).getPlayingStage()==PlayingStage.PLAYING) {
				if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK) {
					
				}
			}
		}
	}

}
