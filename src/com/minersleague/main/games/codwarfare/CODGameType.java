package com.minersleague.main.games.codwarfare;

public enum CODGameType {

	TEAMS("teams"), 
	INFECTED("infected"), 
	FFA("ffa");
	
	public String asString;
	private CODGameType(String asString) {
		this.asString = asString;
	}
	
	public static CODGameType getTypeFromString(String string) {
		if(string!=null) {
			for(CODGameType type : CODGameType.values()) {
				if(type.asString.equals(string)) {
					return type;
				}
			}
		}
		return null;
	}
	
}
