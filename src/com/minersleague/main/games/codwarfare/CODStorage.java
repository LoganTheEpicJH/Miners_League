package com.minersleague.main.games.codwarfare;

import com.minersleague.main.games.generall.PlayerStorage;
import com.minersleague.main.games.generall.PlayingStage;

public class CODStorage extends PlayerStorage {
	
	public String team;
	
	public CODStorage(String gameName, PlayingStage playingStage, String team) {
		super(gameName, playingStage);
		this.team = team;
	}
	
}
