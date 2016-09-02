package com.minersleague.main.towerdefense;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;

public class Towers {

	public static Tower tesla;
	public static Tower blastiodFurnace;

	@SuppressWarnings("deprecation")
	public static void loadTowners() {
		//Blasiod Furnace
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			//y0
			blocks.add(new TowerBlock(0, 0, 0, Material.getMaterial(98)));
			blocks.add(new TowerBlock(0, 0, 1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(1, 0, 1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(1, 0, 0, Material.getMaterial(98)));
			blocks.add(new TowerBlock(1, 0, -1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(0, 0, -1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(-1, 0, -1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(-1, 0, 0, Material.getMaterial(98)));
			blocks.add(new TowerBlock(-1, 0, 1, Material.getMaterial(98)));
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
			blastiodFurnace = new Tower(blocks);
			//Stuff that has to be at the End
			blocks.add(new TowerBlock(0, 1, 0, Material.TNT));
			blocks.add(new TowerBlock(1, 2, 1, Material.SAND));
			blocks.add(new TowerBlock(1, 2, -1, Material.SAND));
			blocks.add(new TowerBlock(-1, 2, -1, Material.SAND));
			blocks.add(new TowerBlock(-1, 2, 1, Material.SAND));
		}
		
		//Tesla
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			blocks.add(new TowerBlock(0, 0, 0, Material.IRON_BLOCK)); //Middle
			blocks.add(new TowerBlock(0, 0, 1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(1, 0, 1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(1, 0, 0, Material.getMaterial(98)));
			blocks.add(new TowerBlock(1, 0, -1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(0, 0, -1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(-1, 0, -1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(-1, 0, 0, Material.getMaterial(98)));
			blocks.add(new TowerBlock(-1, 0, 1, Material.getMaterial(98)));
			blocks.add(new TowerBlock(0, 1, 0, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(0, 2, 0, Material.FENCE));
			blocks.add(new TowerBlock(0, 3, 0, Material.END_ROD, (byte)1));
			tesla = new Tower(blocks);
		}
	}

	public static void buildTowner(long delay, Tower tower, Location location) {
		new Thread(new TowerBuilder(location, tower, delay)).start();;
	}

}
