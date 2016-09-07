package com.minersleague.main.games.generall.util;

import java.util.HashMap;

import com.minersleague.main.games.generall.PlayerStorage;
import com.minersleague.main.games.towerdefense.TDGame;
import com.minersleague.main.games.towerdefense.TowerDefenseHub;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimation;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimator;
import com.minersleague.main.games.towerdefense.mechanics.TDGameRunner;
import com.minersleague.main.games.towerdefense.tower.TowerBreaker;
import com.minersleague.main.games.towerdefense.tower.TowerBuilder;
import com.minersleague.main.games.towerdefense.tower.function.TowerFunction;

public class TDUtils {
	
	public static HashMap<String, TDGame> games;
	public static HashMap<String, PlayerStorage> gameIn;
	public static HashMap<String, Object> idLink;
	public static TowerDefenseHub hub;
	
	public static void loadGames() {
		games = new HashMap<String, TDGame>();
		gameIn = new HashMap<String, PlayerStorage>();
		idLink = new HashMap<String, Object>();
	}
	
	public static void stopAllGameActions(TDGame game) {
		for(String id : idLink.keySet()) {
			if(id.startsWith(game.getName())) {
				Object idLinkObj = idLink.get(id);
				if(id.contains("Animation")) {
					TDAnimation animation = (TDAnimation)idLinkObj;
					animation.stop();
				}
				if(id.contains("GameStarter")) {
					TDGameRunner gs = (TDGameRunner)idLinkObj;
					gs.endGame();
				}
				if(id.contains("Animator")) {
					TDAnimator animator = (TDAnimator)idLinkObj;
					animator.stop();
				}
				if(id.contains("Thread")) {
					Thread thread = (Thread)idLinkObj;
					thread.interrupt();
				}
				if(id.contains("TowerFunction")) {
					TowerFunction tf = (TowerFunction)idLinkObj;
					tf.repeating = false;
				}
				if(id.contains("TowerBuilder")) {
					TowerBuilder tb = (TowerBuilder)idLinkObj;
					new TowerBreaker(tb.location);
					tb.interrupt();
				}
			}
		}
	}
	
}
