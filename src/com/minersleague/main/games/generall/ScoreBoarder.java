package com.minersleague.main.games.generall;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.minersleague.main.Main;
import com.minersleague.main.games.generall.util.CODUtils;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.util.Utilities;

public class ScoreBoarder {

	public static void addScoreBoard(Player p) {
		Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		Objective obj = scoreboard.registerNewObjective("ml_Credits", "dummy");
		obj.setDisplayName(Utilities.color("&e&lCoins"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score spacer = obj.getScore(" ");
		spacer.setScore(3);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				if(TDUtils.gameIn.get(p.getName())!=null||CODUtils.gameIn.get(p.getName())!=null) {
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.IN_LOBBY||CODUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.IN_LOBBY||TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.PLAYING||CODUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.PLAYING) {
						Score coins = obj.getScore(Utilities.color("&aAmount: &f"+p.getMetadata("ml_coins").get(0).asInt()));
						coins.setScore(2);
						p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
						p.setScoreboard(scoreboard);
					} else {
						if(p.getScoreboard().getObjective("ml_credits")!=null) {
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
						}
					}
				} else {
					if(p.getScoreboard().getObjective("ml_credits")!=null) {
						p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					}
				}
			}
		}, 0, 20l);
	}

}
