package com.minersleague.main.towerdefense.tower;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;

import com.minersleague.main.towerdefense.BlockMetaData;
import com.minersleague.main.towerdefense.tower.function.BlastiodFurnaceFunction;
import com.minersleague.main.towerdefense.tower.function.TeslaFunction;

public class Towers {

	public static Tower lpt;
	public static Tower blastiodFurnace;
	public static Tower dmg;
	public static Tower rmg;
	public static Tower boostBeacon;
	public static Tower creditBank;
	public static Tower hpt;
	public static Tower eps;
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
			//Stages
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, -1, Material.SAND));
				replacement.add(new TowerBlock(1, 2, 1, Material.SAND));
				replacement.add(new TowerBlock(-1, 2, 1, Material.SAND));
				replacement.add(new TowerBlock(1, 2, -1, Material.SAND));
				TowerStage stage_0 = new TowerStage(0, false, replacement, 5000, 100, null, 0);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, -1, Material.SAND, new BlockMetaData[]{BlockMetaData.BYTE_1}));
				TowerStage stage_1 = new TowerStage(1, false, replacement, 5000, 1000, null, 0);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(1, 2, -1, Material.SAND, new BlockMetaData[]{BlockMetaData.BYTE_1}));
				TowerStage stage_2 = new TowerStage(2, false, replacement, 5000, 1000, null, 0);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(1, 2, 1, Material.SAND, new BlockMetaData[]{BlockMetaData.BYTE_1}));
				TowerStage stage_3 = new TowerStage(3, false, replacement, 5000, 1000, null, 0);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, 1, Material.SAND, new BlockMetaData[]{BlockMetaData.BYTE_1}));
				TowerStage stage_4 = new TowerStage(4, false, replacement, 5000, 1000, null, 0);
				stages.add(stage_4);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, -1, Material.NETHERRACK));
				replacement.add(new TowerBlock(1, 2, 1, Material.NETHERRACK));
				replacement.add(new TowerBlock(-1, 2, 1, Material.NETHERRACK));
				replacement.add(new TowerBlock(1, 2, -1, Material.NETHERRACK));
				TowerStage stage_5 = new TowerStage(5, true, replacement, 5000, 10, new BlastiodFurnaceFunction(), 7);
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
			blocks.add(new TowerBlock(0, 3, 0, Material.END_ROD, new BlockMetaData[]{BlockMetaData.END_ROD_DOWN}));
			lpt = new Tower(blocks, new TeslaFunction(), 3.5);
		}
		
		//DMG
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(0, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(0, 1, -1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, 0, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 0, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(1, 1, -1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 1, Material.PURPUR_SLAB));
			blocks.add(new TowerBlock(-1, 1, 1, Material.PURPUR_SLAB));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, Material.COBBLE_WALL));
			//Stages
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(1, 1, 1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(1, 1, 0, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(1, 1, -1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(0, 1, -1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(-1, 1, -1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(-1, 1, 0, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(-1, 1, 1, Material.PURPUR_BLOCK));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 2, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(1, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(0, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, 1, Material.AIR));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, Material.AIR));
				replacment.add(new TowerBlock(0, 3, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 3, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 3, 0, Material.AIR));
				replacment.add(new TowerBlock(1, 3, -1, Material.AIR));
				replacment.add(new TowerBlock(0, 3, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 3, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 3, 0, Material.AIR));
				replacment.add(new TowerBlock(-1, 3, 1, Material.AIR));
				TowerStage stage_0 = new TowerStage(0, false, replacment, 5000, 10, null, 0);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(1, 1, 1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(1, 1, 0, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(1, 1, -1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(0, 1, -1, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(-1, 1, -1, Material.PURPUR_BLOCK));
				replacment.add(new TowerBlock(-1, 1, 0, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(-1, 1, 1, Material.PURPUR_BLOCK));
				//y2
				replacment.add(new TowerBlock(0, 2, 0, Material.COBBLE_WALL));
				TowerStage stage_1 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 1, 0, Material.AIR));
				replacment.add(new TowerBlock(0, 1, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 1, 0, Material.AIR));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(1, 2, 1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(1, 2, 0, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(1, 2, -1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(0, 2, -1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(-1, 2, -1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(-1, 2, 0, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(-1, 2, 1, Material.PURPUR_SLAB));
				TowerStage stage_2 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(1, 2, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 2, 0, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(0, 2, -1, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(-1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 2, 0, Material.PURPUR_SLAB, new BlockMetaData[]{BlockMetaData.BYTE_16}));
				replacment.add(new TowerBlock(-1, 2, 1, Material.NETHER_BRICK));
				TowerStage stage_3 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 2, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(0, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, 1, Material.NETHER_BRICK));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(0, 3, 1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(1, 3, 1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(1, 3, 0, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(1, 3, -1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(0, 3, -1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(-1, 3, -1, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(-1, 3, 0, Material.PURPUR_SLAB));
				replacment.add(new TowerBlock(-1, 3, 1, Material.PURPUR_SLAB));
				TowerStage stage_4 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_4);
			}
			dmg = new Tower(blocks, false, null);
		}
		
		//RMG
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, Material.NETHERRACK));
			blocks.add(new TowerBlock(0, 1, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(1, 1, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(1, 1, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(1, 1, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(0, 1, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(-1, 1, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(-1, 1, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			blocks.add(new TowerBlock(-1, 1, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, Material.COBBLE_WALL));
			//Stages
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 1, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 1, 0, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 1, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(0, 1, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 1, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 1, 0, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 1, 1, Material.NETHER_BRICK));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 2, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(1, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(0, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, 1, Material.AIR));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, Material.AIR));
				replacment.add(new TowerBlock(0, 3, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 3, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 3, 0, Material.AIR));
				replacment.add(new TowerBlock(1, 3, -1, Material.AIR));
				replacment.add(new TowerBlock(0, 3, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 3, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 3, 0, Material.AIR));
				replacment.add(new TowerBlock(-1, 3, 1, Material.AIR));
				TowerStage stage_0 = new TowerStage(0, false, replacment, 5000, 10, null, 0);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(1, 1, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 1, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(1, 1, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(0, 1, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(-1, 1, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 1, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(-1, 1, 1, Material.NETHER_BRICK));
				//y2
				replacment.add(new TowerBlock(0, 2, 0, Material.COBBLE_WALL));
				TowerStage stage_1 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 1, 0, Material.AIR));
				replacment.add(new TowerBlock(0, 1, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 1, 0, Material.AIR));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(1, 2, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(1, 2, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(1, 2, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(0, 2, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(-1, 2, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(-1, 2, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(-1, 2, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				TowerStage stage_2 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(1, 2, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 2, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(0, 2, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(-1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 2, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_14}));
				replacment.add(new TowerBlock(-1, 2, 1, Material.NETHER_BRICK));
				TowerStage stage_3 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, Material.AIR));
				replacment.add(new TowerBlock(1, 2, 1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(0, 2, -1, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, -1, Material.NETHER_BRICK));
				replacment.add(new TowerBlock(-1, 2, 0, Material.AIR));
				replacment.add(new TowerBlock(-1, 2, 1, Material.NETHER_BRICK));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(0, 3, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(1, 3, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(1, 3, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(1, 3, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(0, 3, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(-1, 3, -1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(-1, 3, 0, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				replacment.add(new TowerBlock(-1, 3, 1, Material.STONE_SLAB2, new BlockMetaData[]{BlockMetaData.BYTE_6}));
				TowerStage stage_4 = new TowerStage(0, false, replacment, 5000, 2, null, 0);
				stages.add(stage_4);
			}
			rmg = new Tower(blocks, true, stages);
		}
		
		//BoostBeacon
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.IRON_BLOCK)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(1, 0, 1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(1, 0, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(1, 0, -1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(0, 0, -1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.IRON_BLOCK));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, Material.BEACON));
			boostBeacon = new Tower(blocks, false, null);
		}
		
		//CreditBank
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.SMOOTH_BRICK));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, Material.IRON_ORE));
			blocks.add(new TowerBlock(0, 1, 1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(1, 1, 0, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(0, 1, -1, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(-1, 1, 0, Material.COBBLE_WALL));
			creditBank = new Tower(blocks, false, null);
		}
		
		//HPT
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.SMOOTH_BRICK)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.DISPENSER, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.DISPENSER, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.DISPENSER, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.DISPENSER, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, Material.COMMAND_REPEATING, new BlockMetaData[]{BlockMetaData.BYTE_15}));
			blocks.add(new TowerBlock(1, 1, 1, Material.FENCE));
			blocks.add(new TowerBlock(1, 1, -1, Material.FENCE));
			blocks.add(new TowerBlock(-1, 1, -1, Material.FENCE));
			blocks.add(new TowerBlock(-1, 1, 1, Material.FENCE));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(1, 2, 1, Material.IRON_FENCE));
			blocks.add(new TowerBlock(1, 2, -1, Material.IRON_FENCE));
			blocks.add(new TowerBlock(-1, 2, -1, Material.IRON_FENCE));
			blocks.add(new TowerBlock(-1, 2, 1, Material.IRON_FENCE));
			//y3
			blocks.add(new TowerBlock(0, 3, 0, Material.FENCE));
			//y4
			blocks.add(new TowerBlock(0, 4, 0, Material.END_ROD, new BlockMetaData[]{BlockMetaData.END_ROD_DOWN}));
			hpt = new Tower(blocks, false, null);
		}
		
		//EPS
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.REDSTONE_LAMP_OFF)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, 1, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(1, 0, -1, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(0, 0, -1, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, -1, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(-1, 0, 0, Material.SMOOTH_BRICK));
			blocks.add(new TowerBlock(-1, 0, 1, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			//y1
			blocks.add(new TowerBlock(0, 1, 1, Material.REDSTONE_WIRE));
			blocks.add(new TowerBlock(1, 1, 1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(1, 1, 0, Material.REDSTONE_WIRE));
			blocks.add(new TowerBlock(1, 1, -1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(0, 1, -1, Material.REDSTONE_WIRE));
			blocks.add(new TowerBlock(-1, 1, -1, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(-1, 1, 0, Material.REDSTONE_WIRE));
			blocks.add(new TowerBlock(-1, 1, 1, Material.IRON_BLOCK));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, Material.REDSTONE_BLOCK)); //Middle
			blocks.add(new TowerBlock(0, 2, 1, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_9}));
			blocks.add(new TowerBlock(1, 2, 0, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_9}));
			blocks.add(new TowerBlock(0, 2, -1, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_9}));
			blocks.add(new TowerBlock(-1, 2, 0, Material.PISTON_STICKY_BASE, new BlockMetaData[]{BlockMetaData.BYTE_9}));
			//y3
			blocks.add(new TowerBlock(0, 3, 0, Material.PISTON_STICKY_BASE)); //Middle
			blocks.add(new TowerBlock(0, 3, 1, Material.getMaterial(34), new BlockMetaData[]{BlockMetaData.BYTE_9}));
			blocks.add(new TowerBlock(1, 3, 0, Material.getMaterial(34), new BlockMetaData[]{BlockMetaData.BYTE_9}));
			blocks.add(new TowerBlock(0, 3, -1, Material.getMaterial(34), new BlockMetaData[]{BlockMetaData.BYTE_9}));
			blocks.add(new TowerBlock(-1, 3, 0, Material.getMaterial(34), new BlockMetaData[]{BlockMetaData.BYTE_9}));
			//y4
			blocks.add(new TowerBlock(0, 4, 0, Material.WOOL, new BlockMetaData[]{BlockMetaData.BYTE_14})); //Middle
			blocks.add(new TowerBlock(0, 4, 1, Material.SMOOTH_STAIRS, new BlockMetaData[]{BlockMetaData.BYTE_1}));
			blocks.add(new TowerBlock(1, 4, 0, Material.SMOOTH_STAIRS));
			blocks.add(new TowerBlock(0, 4, -1, Material.SMOOTH_STAIRS, new BlockMetaData[]{BlockMetaData.BYTE_3}));
			blocks.add(new TowerBlock(-1, 4, 0, Material.SMOOTH_STAIRS, new BlockMetaData[]{BlockMetaData.BYTE_2}));
			eps = new Tower(blocks, false, null);
		}
		
		towers.put("blastiod", blastiodFurnace);
		towers.put("dmg", dmg);
		towers.put("rmg", rmg);
		towers.put("lpt", lpt);
		towers.put("beacon", boostBeacon);
		towers.put("bank", creditBank);
		towers.put("hpt", hpt);
		towers.put("eps", eps);
	}

	public static void buildTowner(String gameName, String towerID, long delay, Tower tower, Location location) {
		new TowerBuilder(gameName, towerID, location, tower, delay);
		//Utilities.builders.add(tb);
	}

}
