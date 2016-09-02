package com.minersleague.main.towerdefense;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

import com.minersleague.main.Main;

public class Towers {

	public static Tower tesla;
	public static HashMap<Integer, Boolean> tasks;
	
	public static void loadTowners() {
		tasks = new HashMap<Integer, Boolean>();
		{
			ArrayList<TowerBlock> blocks = new ArrayList<TowerBlock>();
			blocks.add(new TowerBlock(0, 0, 0, Material.IRON_BLOCK));
			blocks.add(new TowerBlock(0, 0, 1, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(1, 0, 1, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(1, 0, 0, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(1, 0, -1, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(0, 0, -1, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(-1, 0, -1, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(-1, 0, 0, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(-1, 0, 1, Material.getMaterial("stonebrick")));
			blocks.add(new TowerBlock(0, 1, 0, Material.COBBLE_WALL));
			blocks.add(new TowerBlock(0, 2, 0, Material.FENCE));
			blocks.add(new TowerBlock(0, 3, 0, Material.END_ROD, (byte)1));
			tesla = new Tower(blocks);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void buildTowner(long delay, Tower tower, Location location) {
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.plugin, new BukkitRunnable() {
			int at = 0;
			int x = location.getBlockX();
			int y = location.getBlockY();
			int z = location.getBlockZ();
			@Override
			public void run() {
				ArrayList<TowerBlock> blocks = tower.getBlocks();
				if(!(at>blocks.size())) {
					TowerBlock block = blocks.get(at);
					Block worldBlock = location.getWorld().getBlockAt(x+block.x, y+block.y, z+block.z);  
					worldBlock.setType(block.getMaterial());
					if(block.direction>0) {
						worldBlock.getState().setData(new MaterialData(block.getMaterial(), block.direction));
					}
					worldBlock.getState().update();
					at++;
				} else {
					cancel();
				}
			}
		}, 0, delay);
	}
	
}
