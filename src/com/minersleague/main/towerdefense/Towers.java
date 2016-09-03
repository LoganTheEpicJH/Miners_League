package com.minersleague.main.towerdefense;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

public class Towers {

	public static Tower lpt;
	public static Tower blastiodFurnace;
	public static Tower dmg;
	public static Tower rmg;
	public static HashMap<String, Tower> towers;
	
	@SuppressWarnings("deprecation")
	public static void loadTowners() {
		towers = new HashMap<String, Tower>();
		//Blasiod Furnace
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			//y1
			blocks.add(new TowerBlock(0, 1, 1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(1, 1, 1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(1, 1, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(1, 1, -1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(0, 1, -1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(-1, 1, -1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(-1, 1, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(-1, 1, 1, Material.COBBLE_WALL));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, Material.AIR));
			blocks.add(new TowerBlock(0, 2, 1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(1, 2, 0, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(0, 2, -1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(-1, 2, 0, Material.COBBLE_WALL));
			//Stuff that has to be at the End
			blocks.add(new TowerBlock(0, 1, 0, Material.TNT));
			blocks.add(new TowerBlock(1, 2, 1, Material.SAND));
			blocks.add(new TowerBlock(1, 2, -1, Material.SAND));
			blocks.add(new TowerBlock(-1, 2, -1, Material.SAND));
			blocks.add(new TowerBlock(-1, 2, 1, Material.SAND));
			//Stage 1
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			blastiodFurnace = new Tower(blocks, true, stages);
		}
		
		//LPT
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			blocks.add(new TowerBlock(0, 0, 0, Material.IRON_BLOCK)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 1, 0, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(0, 2, 0, Material.FENCE));
			blocks.add(new TowerBlock(0, 3, 0, Material.END_ROD, (byte)1));
			lpt = new Tower(blocks, false, null);
		}
		
		//DMG
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 1, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(0, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(0, 1, -1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, 0, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 0, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, -1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(0, 3, 0, Material.COBBLE_WALL));
			dmg = new Tower(blocks, false, null);
		}
		
		//RMG
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 1, 0, Material.NETHERRACK));
			blocks.add(new TowerBlock(0, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(0, 1, -1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, 0, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 0, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, -1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(0, 3, 0, Material.COBBLE_WALL));
			rmg = new Tower(blocks, false, null);
		}
		
		towers.put("blastiod", blastiodFurnace);
		towers.put("dmg", dmg);
		towers.put("rmg", rmg);
		towers.put("lpt", lpt);
	}

	public static void buildTowner(long delay, Tower tower, Location location) {
		new Thread(new TowerBuilder(location, tower, delay)).start();
	}

}
