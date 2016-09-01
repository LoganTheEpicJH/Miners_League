package com.minersleague.main.permissions;

import java.util.ArrayList;

public class Groups {

	public static Group miner;
	public static ArrayList<World> worlds;
	
	public static void init() {
		//Groups
		miner = new Group("Miner");
		
		//Lists
		worlds = new ArrayList<World>();
		
	}
	
}
