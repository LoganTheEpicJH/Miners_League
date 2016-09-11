package com.minersleague.main.games.codwarfare;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import com.minersleague.main.Main;
import com.minersleague.main.games.generall.util.CODUtils;

public enum Guns {
	
	Barret_50cal(50, Material.BLAZE_ROD),
	Handgun_45ACP(10, Material.LEVER);
	
	private int damage;
	private Material material;
	
	private Guns(int damage, Material material) {
		this.damage = damage;
		this.material = material;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public static Guns getGunfromMaterial(Material material) {
		if(material!=null) {
			for(Guns gun : Guns.values()) {
				if(material.equals(gun.getMaterial())) {
					return gun;
				}
			}
		}
		return null;
	}
	
	public static void shootGun(Player p) {
		double pitch = ((p.getLocation().getPitch()+90)*Math.PI)/180;
		double yaw = ((p.getLocation().getYaw()+90)*Math.PI)/180;
		double x = Math.sin(pitch)*Math.cos(yaw);
		double y = Math.sin(pitch)*Math.sin(yaw);
		double z = Math.cos(pitch);
		Vector v = new Vector(x, z, y);
		v.multiply(5.0d);
		//p.setVelocity(v);
		Snowball ball = p.launchProjectile(Snowball.class); 
		ball.setMetadata(GunMetaData.GUN_SHOOTER.key, new FixedMetadataValue(Main.plugin, p.getName()));
		ball.setMetadata(GunMetaData.GUN_SHOOTER_TEAM.key, new FixedMetadataValue(Main.plugin, CODUtils.getTeamPlayerIsIn(p)));
		ball.setMetadata(GunMetaData.GUN_DAMAGE.key, new FixedMetadataValue(Main.plugin, Guns.getGunfromMaterial(p.getInventory().getItemInMainHand().getType()).getDamage()));
		ball.setVelocity(v);
	}
	
}
