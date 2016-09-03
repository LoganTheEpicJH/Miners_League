package com.minersleague.main.towerdefense.tower;

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
			{
				ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				oldBlocks.add(new TowerBlock(-1, 2, -1, Material.NETHERRACK));
				replacement.add(new TowerBlock(-1, 2, -1, Material.SAND));
				oldBlocks.add(new TowerBlock(1, 2, 1, Material.NETHERRACK));
				replacement.add(new TowerBlock(1, 2, 1, Material.SAND));
				oldBlocks.add(new TowerBlock(-1, 2, 1, Material.NETHERRACK));
				replacement.add(new TowerBlock(-1, 2, 1, Material.SAND));
				oldBlocks.add(new TowerBlock(1, 2, -1, Material.NETHERRACK));
				replacement.add(new TowerBlock(1, 2, -1, Material.SAND));
				TowerStage stage_0 = new TowerStage(0, false, oldBlocks, replacement, 5000, 1000);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				oldBlocks.add(new TowerBlock(-1, 2, -1, Material.SAND));
				replacement.add(new TowerBlock(-1, 2, -1, Material.STONE));
				TowerStage stage_1 = new TowerStage(1, false, oldBlocks, replacement, 5000, 1000);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				oldBlocks.add(new TowerBlock(1, 2, -1, Material.SAND));
				replacement.add(new TowerBlock(1, 2, -1, Material.STONE));
				TowerStage stage_2 = new TowerStage(2, false, oldBlocks, replacement, 5000, 1000);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				oldBlocks.add(new TowerBlock(1, 2, 1, Material.SAND));
				replacement.add(new TowerBlock(1, 2, 1, Material.STONE));
				TowerStage stage_3 = new TowerStage(3, false, oldBlocks, replacement, 5000, 1000);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				oldBlocks.add(new TowerBlock(-1, 2, 1, Material.SAND));
				replacement.add(new TowerBlock(-1, 2, 1, Material.STONE));
				TowerStage stage_4 = new TowerStage(4, false, oldBlocks, replacement, 5000, 1000);
				stages.add(stage_4);
			}
			{
				ArrayList<TowerBlock> oldBlocks = new ArrayList<TowerBlock>();
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				oldBlocks.add(new TowerBlock(-1, 2, -1, Material.STONE));
				replacement.add(new TowerBlock(-1, 2, -1, Material.NETHERRACK));
				oldBlocks.add(new TowerBlock(1, 2, 1, Material.STONE));
				replacement.add(new TowerBlock(1, 2, 1, Material.NETHERRACK));
				oldBlocks.add(new TowerBlock(-1, 2, 1, Material.STONE));
				replacement.add(new TowerBlock(-1, 2, 1, Material.NETHERRACK));
				oldBlocks.add(new TowerBlock(1, 2, -1, Material.STONE));
				replacement.add(new TowerBlock(1, 2, -1, Material.NETHERRACK));
				TowerStage stage_5 = new TowerStage(5, true, oldBlocks, replacement, 5000, 1000);
				stages.add(stage_5);
			}
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