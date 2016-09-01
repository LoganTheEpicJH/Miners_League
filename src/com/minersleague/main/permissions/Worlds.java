package com.minersleague.main.permissions;

import java.util.HashMap;
import java.util.HashSet;

import com.minersleague.main.Main;

public class Worlds {

	public static World hub() {
		HashSet<String> perms = new HashSet<String>();
		perms.add("fatherboard.group.hub");
		perms.add("multiverse.core.spawn.self");
		perms.add("poll.command.vote");
		perms.add("ultracosmetics.openmemu");
		HashMap<Group, HashSet<String>> access = new HashMap<Group, HashSet<String>>();
		access.put(Main.miner, perms);
		return new World("Hub", access);
	}
	
}