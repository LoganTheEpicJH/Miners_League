package com.minersleague.main.permissions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.permissions.PermissionAttachment;

public class Groups {

	public static Group miner;
	public static HashMap<String, World> worlds;
	public static HashMap<UUID, PermissionAttachment> attachments;
	public static HashMap<String, Group> groups;
	
	public static void init() {
		
		//Groups
		miner = new Group("Miner", "&3");
		
		//Lists
		worlds = new HashMap<String, World>();
		attachments = new HashMap<UUID, PermissionAttachment>();
		groups = new HashMap<String, Group>();
		
		//Init
		worlds.put("Hub", Worlds.hub());
		groups.put("Miner", miner);
		
	}
	
}
