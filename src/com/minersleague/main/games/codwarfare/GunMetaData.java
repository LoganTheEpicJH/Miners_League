package com.minersleague.main.games.codwarfare;

public enum GunMetaData {

	GUN_SHOOTER("ml_gun_shooter"),
	GUN_SHOOTER_TEAM("ml_gun_team"),
	GUN_DAMAGE("ml_gun_damage");
	
	public String key;
	private GunMetaData(String asValue) {
		this.key = asValue;
	}
	
}
