package com.minersleague.main.permissions;

import java.util.HashMap;
import java.util.HashSet;

public class Worlds {

	public static World hub() {
		HashSet<String> perms_miner = new HashSet<String>();
		perms_miner.add("fatherboard.group.hub");
		perms_miner.add("multiverse.core.spawn.self");
		perms_miner.add("poll.command.vote");
		perms_miner.add("ultracosmetics.openmemu");
		HashMap<Group, HashSet<String>> access = new HashMap<Group, HashSet<String>>();
		access.put(Groups.miner, perms_miner);
		return new World("Hub", access);
	}
	
}
