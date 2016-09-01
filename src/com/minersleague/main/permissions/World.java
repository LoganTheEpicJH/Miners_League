package com.minersleague.main.permissions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class World {
	
	private HashMap<Group, HashSet<String>> permissions; 
	
	public World() {}
	
	public World(String name, HashMap<Group, HashSet<String>> permissions) {
		this.permissions = permissions;
	}
	
	public HashMap<Group, HashSet<String>> getGroupsAndPermissions() {
		return permissions;
	}
	
}
