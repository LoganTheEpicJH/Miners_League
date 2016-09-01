package com.minersleague.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.minersleague.main.commands.CMD_Test;
import com.minersleague.main.commands.MinersLeagueCommand;
import com.minersleague.main.permissions.Group;
import com.minersleague.main.permissions.World;

public class Main extends JavaPlugin {

	public List<World> worlds;
	public static Group miner;
	
	@Override
	public void onEnable() {
		worlds = new ArrayList<World>();
		worlds.add(new World("Hub", (Map<Group, Set<String>>)new HashMap<Group, Set<String>>()));
		miner = new Group("Miner");
		/**We dont need this, a Plugin gives a Enabel and Disable Logger Info on its own!**/
		/*PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdfFile.getName()+" has been enabled!");*/
		
		//Commands
		registerCommand(new CMD_Test());
		
	}

	@Override
	public void onDisable() {
		/*PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdfFile.getName()+" has been disabled!");*/
	}
	
	public void registerCommand(MinersLeagueCommand cmd) {
		getCommand(cmd.getName()).setExecutor(cmd);
	}
	
	public void registerListener(Listener listener) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, this);
	}
	
}
