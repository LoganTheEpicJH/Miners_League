package com.minersleague.main.games.towerdefense.tower;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimation;

public class TowerBuilder extends SimpleThread {

	private int x, y, z;
	public Location location;
	public Tower tower;
	public boolean done;
	private int at;
	private long delay;
	private TowerBuilder towerBuilder;
	public String id;
	private String gameName;
	private String towerID;

	public TowerBuilder(String gameName, String towerID, Location location, Tower tower, long delay) {
		this.gameName = gameName;
		this.towerID = towerID;
		id = setID(gameName+"-TowerBuilder"+towerID+TDUtils.games.get(gameName).getNextTowerID());
		//System.out.println(id);
		this.location = location;
		this.tower = tower;
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
		at = 0;
		this.delay = delay;
		done = false;
		towerBuilder = this;
		executeThread(towerBuilder);
		TDUtils.idLink.put(id, towerBuilder);
	}
	
	public void interrupt() {
		try {
			Class.forName("com.minersleague.main.games.towerdefense.tower.TowerBreaker").getConstructor(new Class[]{Tower.class, Location.class}).newInstance(new Object[]{tower, location});
		} catch(Exception e) {
			e.printStackTrace();
		}
		cancelThread();
		done = true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(!done) {
			if(!(at+1>tower.getBlocks().size())) {
				// System.out.println("Done: "+done+" At+1: "+(at+1)+" At: "+at+" Size: "+tower.getBlocks().size());
				if(tower.getBlocks().get(at)!=null) {
					TowerBlock block = tower.getBlocks().get(at);
					Block worldBlock = location.getWorld().getBlockAt(x+block.x, y+block.y, z+block.z);
					worldBlock.setType(block.getBlock().getMaterial());
					if(block.getBlock().getBlockMetaData()>0) {
							worldBlock.setData((byte)block.getBlock().getBlockMetaData(), true);
							worldBlock.getState().update();
					}
					done = false;
					at++;
					try {
						Thread.sleep(delay);
					} catch(InterruptedException e) {}
				} else {
					done = true;
					// System.out.println("Interruption 1 | A Block is Null TowerBuilder:50");
				}
			} else {
				if(tower.hasStages()) {
					new TDAnimation(gameName+"-"+towerID+TDUtils.games.get(gameName).getNextTowerID(), towerBuilder);
				} /*else if(tower.getRepeatingFunction()!=null) {
					try {
						Thread.sleep(10);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					TowerFunction tf = tower.getRepeatingFunction();
					tf.init(location.clone(), tower.getRadius());
					tf.start();
					TDUtils.idLink.put(gameName+"-TowerFunction"+TDUtils.games.get(gameName).getNextTowerID(), tf);
					System.out.println("Repeating Thread Started");
				}*/
				done = true;
				// System.out.println("Interruption 2 | Done");
			}
		}
	}

}
