package com.minersleague.main.games.towerdefense.tower;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;

import com.minersleague.main.util.BlockResource;

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
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Stone_Bricks));
			//y1
			blocks.add(new TowerBlock(0, 1, 1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(1, 1, 1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(1, 1, 0, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(1, 1, -1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(0, 1, -1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(-1, 1, -1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(-1, 1, 0, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(-1, 1, 1, BlockResource.Cobblestone_Wall));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, BlockResource.Air));
			blocks.add(new TowerBlock(0, 2, 1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(1, 2, 0, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(0, 2, -1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(-1, 2, 0, BlockResource.Cobblestone_Wall));
			//Stuff that has to be at the End
			blocks.add(new TowerBlock(0, 1, 0, BlockResource.TNT));
			blocks.add(new TowerBlock(1, 2, 1, BlockResource.Sand));
			blocks.add(new TowerBlock(1, 2, -1, BlockResource.Sand));
			blocks.add(new TowerBlock(-1, 2, -1, BlockResource.Sand));
			blocks.add(new TowerBlock(-1, 2, 1, BlockResource.Sand));
			//Stages
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, -1, BlockResource.Sand));
				replacement.add(new TowerBlock(1, 2, 1, BlockResource.Sand));
				replacement.add(new TowerBlock(-1, 2, 1, BlockResource.Sand));
				replacement.add(new TowerBlock(1, 2, -1, BlockResource.Sand));
				TowerStage stage_0 = new TowerStage(0, false, replacement, 5000, 100);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, -1, BlockResource.Red_Sand));
				TowerStage stage_1 = new TowerStage(1, false, replacement, 5000, 1000);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(1, 2, -1, BlockResource.Red_Sand));
				TowerStage stage_2 = new TowerStage(2, false, replacement, 5000, 1000);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(1, 2, 1, BlockResource.Red_Sand));
				TowerStage stage_3 = new TowerStage(3, false, replacement, 5000, 1000);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, 1, BlockResource.Red_Sand));
				TowerStage stage_4 = new TowerStage(4, false, replacement, 5000, 1000);
				stages.add(stage_4);
			}
			{
				ArrayList<TowerBlock> replacement = new ArrayList<TowerBlock>();
				replacement.add(new TowerBlock(-1, 2, -1, BlockResource.Netherrack));
				replacement.add(new TowerBlock(1, 2, 1, BlockResource.Netherrack));
				replacement.add(new TowerBlock(-1, 2, 1, BlockResource.Netherrack));
				replacement.add(new TowerBlock(1, 2, -1, BlockResource.Netherrack));
				TowerStage stage_5 = new TowerStage(5, true, replacement, 5000, 10);
				stages.add(stage_5);
			}
			blastiodFurnace = new Tower(blocks, true, stages);
		}
		
		//LPT
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Iron_Block)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 1, 0, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(0, 2, 0, BlockResource.Oak_Fence));
			blocks.add(new TowerBlock(0, 3, 0, BlockResource.End_Rod_Down));
			lpt = new Tower(blocks);
		}
		
		//DMG
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(0, 1, 1, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(0, 1, -1, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(1, 1, 0, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(-1, 1, 0, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(1, 1, 1, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(1, 1, -1, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(-1, 1, 1, BlockResource.Purpur_Slab));
			blocks.add(new TowerBlock(-1, 1, -1, BlockResource.Purpur_Slab));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, BlockResource.Cobblestone_Wall));
			//Stages
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(1, 1, 1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(1, 1, 0, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(1, 1, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(0, 1, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(-1, 1, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(-1, 1, 0, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(-1, 1, 1, BlockResource.Purpur_Block));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Air));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, BlockResource.Air));
				replacment.add(new TowerBlock(0, 3, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 3, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 3, 0, BlockResource.Air));
				replacment.add(new TowerBlock(1, 3, -1, BlockResource.Air));
				replacment.add(new TowerBlock(0, 3, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 3, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 3, 0, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 3, 1, BlockResource.Air));
				TowerStage stage_0 = new TowerStage(0, false, replacment, 5000, 10);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(1, 1, 1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(1, 1, 0, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(1, 1, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(0, 1, -1, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(-1, 1, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(-1, 1, 0, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(-1, 1, 1, BlockResource.Purpur_Block));
				//y2
				replacment.add(new TowerBlock(0, 2, 0, BlockResource.Cobblestone_Wall));
				TowerStage stage_1 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 1, 0, BlockResource.Air));
				replacment.add(new TowerBlock(0, 1, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 1, 0, BlockResource.Air));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Purpur_Slab));
				TowerStage stage_2 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Purpur_Slab_UP));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Purpur_Block));
				TowerStage stage_3 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Purpur_Block));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Purpur_Block));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(0, 3, 1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(1, 3, 1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(1, 3, 0, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(1, 3, -1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(0, 3, -1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(-1, 3, -1, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(-1, 3, 0, BlockResource.Purpur_Slab));
				replacment.add(new TowerBlock(-1, 3, 1, BlockResource.Purpur_Slab));
				TowerStage stage_4 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_4);
			}
			dmg = new Tower(blocks, true, stages);
		}
		
		//RMG
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, BlockResource.Netherrack));
			blocks.add(new TowerBlock(0, 1, 1, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(1, 1, 1, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(1, 1, 0, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(1, 1, -1, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(0, 1, -1, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(-1, 1, -1, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(-1, 1, 0, BlockResource.Nether_Brick_Slab));
			blocks.add(new TowerBlock(-1, 1, 1, BlockResource.Nether_Brick_Slab));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, BlockResource.Cobblestone_Wall));
			//Stages
			ArrayList<TowerStage> stages = new ArrayList<TowerStage>();
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(1, 1, 1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(1, 1, 0, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(1, 1, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(0, 1, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(-1, 1, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(-1, 1, 0, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(-1, 1, 1, BlockResource.Nether_Brick));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Air));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, BlockResource.Air));
				replacment.add(new TowerBlock(0, 3, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 3, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 3, 0, BlockResource.Air));
				replacment.add(new TowerBlock(1, 3, -1, BlockResource.Air));
				replacment.add(new TowerBlock(0, 3, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 3, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 3, 0, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 3, 1, BlockResource.Air));
				TowerStage stage_0 = new TowerStage(0, false, replacment, 5000, 10);
				stages.add(stage_0);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(1, 1, 1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(1, 1, 0, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(1, 1, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(0, 1, -1, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(-1, 1, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(-1, 1, 0, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(-1, 1, 1, BlockResource.Nether_Brick));
				//y2
				replacment.add(new TowerBlock(0, 2, 0, BlockResource.Cobblestone_Wall));
				TowerStage stage_1 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_1);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y1
				replacment.add(new TowerBlock(0, 1, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 1, 0, BlockResource.Air));
				replacment.add(new TowerBlock(0, 1, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 1, 0, BlockResource.Air));
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Nether_Brick_Slab));
				TowerStage stage_2 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_2);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Nether_Brick_Slab_UP));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Nether_Brick));
				TowerStage stage_3 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_3);
			}
			{
				ArrayList<TowerBlock> replacment = new ArrayList<TowerBlock>();
				//y2
				replacment.add(new TowerBlock(0, 2, 1, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, 1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(1, 2, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(0, 2, -1, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, -1, BlockResource.Nether_Brick));
				replacment.add(new TowerBlock(-1, 2, 0, BlockResource.Air));
				replacment.add(new TowerBlock(-1, 2, 1, BlockResource.Nether_Brick));
				//y3
				replacment.add(new TowerBlock(0, 3, 0, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(0, 3, 1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(1, 3, 1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(1, 3, 0, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(1, 3, -1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(0, 3, -1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(-1, 3, -1, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(-1, 3, 0, BlockResource.Nether_Brick_Slab));
				replacment.add(new TowerBlock(-1, 3, 1, BlockResource.Nether_Brick_Slab));
				TowerStage stage_4 = new TowerStage(0, false, replacment, 5000, 2);
				stages.add(stage_4);
			}
			rmg = new Tower(blocks, true, stages);
		}
		
		//BoostBeacon
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Iron_Block)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Iron_Block));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Iron_Block));
			//y1
			//blocks.add(new TowerBlock(0, 1, 0, "beacon"));
			boostBeacon = new Tower(blocks);
		}
		
		//CreditBank
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Stone_Bricks)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Stone_Bricks));
			//y1
			blocks.add(new TowerBlock(0, 1, 0, BlockResource.Iron_Ore));
			blocks.add(new TowerBlock(0, 1, 1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(1, 1, 0, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(0, 1, -1, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(-1, 1, 0, BlockResource.Cobblestone_Wall));
			creditBank = new Tower(blocks);
		}
		
		//HPT
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, BlockResource.Stone_Bricks)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Dispenser_UP));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Dispenser_UP));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Dispenser_UP));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Dispenser_UP));
			//y1
			//blocks.add(new TowerBlock(0, 1, 0, "conditional-Repeating-Commandblock"));
			blocks.add(new TowerBlock(1, 1, 1, BlockResource.Oak_Fence));
			blocks.add(new TowerBlock(1, 1, -1, BlockResource.Oak_Fence));
			blocks.add(new TowerBlock(-1, 1, -1, BlockResource.Oak_Fence));
			blocks.add(new TowerBlock(-1, 1, 1, BlockResource.Oak_Fence));
			//y2
			blocks.add(new TowerBlock(0, 2, 0, BlockResource.Cobblestone_Wall));
			blocks.add(new TowerBlock(1, 2, 1, BlockResource.Iron_Bars));
			blocks.add(new TowerBlock(1, 2, -1, BlockResource.Iron_Bars));
			blocks.add(new TowerBlock(-1, 2, -1, BlockResource.Iron_Bars));
			blocks.add(new TowerBlock(-1, 2, 1, BlockResource.Iron_Bars));
			//y3
			blocks.add(new TowerBlock(0, 3, 0, BlockResource.Oak_Fence));
			//y4
			blocks.add(new TowerBlock(0, 4, 0, BlockResource.End_Rod_Down));
			hpt = new Tower(blocks);
		}
		
		//EPS
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			//blocks.add(new TowerBlock(0, 0, 0, "redstonelamp_off")); //Middle
			blocks.add(new TowerBlock(0, 0, 1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, 1, BlockResource.Sticky_Piston_UP));
			blocks.add(new TowerBlock(1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(1, 0, -1, BlockResource.Sticky_Piston_UP));
			blocks.add(new TowerBlock(0, 0, -1, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, -1, BlockResource.Sticky_Piston_UP));
			blocks.add(new TowerBlock(-1, 0, 0, BlockResource.Stone_Bricks));
			blocks.add(new TowerBlock(-1, 0, 1, BlockResource.Sticky_Piston_UP));
			//y1
			//blocks.add(new TowerBlock(0, 1, 1, "redstone-wire"));
			blocks.add(new TowerBlock(1, 1, 1, BlockResource.Iron_Block));
			//blocks.add(new TowerBlock(1, 1, 0, "redstone-wire"));
			blocks.add(new TowerBlock(1, 1, -1, BlockResource.Iron_Block));
			//blocks.add(new TowerBlock(0, 1, -1, "redstone-wire"));
			blocks.add(new TowerBlock(-1, 1, -1, BlockResource.Iron_Block));
			//blocks.add(new TowerBlock(-1, 1, 0, "redstone-wire"));
			blocks.add(new TowerBlock(-1, 1, 1, BlockResource.Iron_Block));
			//y2
			//blocks.add(new TowerBlock(0, 2, 0, "redstone-block")); //Middle
			blocks.add(new TowerBlock(0, 2, 1, BlockResource.Extended_Sticky_Piston_UP));
			blocks.add(new TowerBlock(1, 2, 0, BlockResource.Extended_Sticky_Piston_UP));
			blocks.add(new TowerBlock(0, 2, -1, BlockResource.Extended_Sticky_Piston_UP));
			blocks.add(new TowerBlock(-1, 2, 0, BlockResource.Extended_Sticky_Piston_UP));
			//y3
			blocks.add(new TowerBlock(0, 3, 0, BlockResource.Sticky_Piston_Down)); //Middle
			blocks.add(new TowerBlock(0, 3, 1, BlockResource.Sticky_Piston_Head_UP));
			blocks.add(new TowerBlock(1, 3, 0, BlockResource.Sticky_Piston_Head_UP));
			blocks.add(new TowerBlock(0, 3, -1, BlockResource.Sticky_Piston_Head_UP));
			blocks.add(new TowerBlock(-1, 3, 0, BlockResource.Sticky_Piston_Head_UP));
			//y4
			blocks.add(new TowerBlock(0, 4, 0, BlockResource.Red_Wool)); //Middle
			blocks.add(new TowerBlock(0, 4, 1, BlockResource.Stone_Brick_Stairs_North));
			blocks.add(new TowerBlock(1, 4, 0, BlockResource.Stone_Brick_Stairs_West));
			blocks.add(new TowerBlock(0, 4, -1, BlockResource.Stone_Brick_Stairs_South));
			blocks.add(new TowerBlock(-1, 4, 0, BlockResource.Stone_Brick_Stairs_East));
			eps = new Tower(blocks);
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
