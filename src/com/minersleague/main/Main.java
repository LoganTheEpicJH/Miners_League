package com.minersleague.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.minersleague.main.commands.CMD_Announcement;
import com.minersleague.main.commands.CMD_Test;
import com.minersleague.main.commands.MinersLeagueCommand;
import com.minersleague.main.permissions.Groups;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		Groups.init();
		/**We dont need this, a Plugin gives a Enabel and Disable Logger Info on its own!**/
		/*PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
		logger.info(pdfFile.getName()+" has been enabled!");*/
		
		//Commands
		registerCommand(new CMD_Test());
		registerCommand(new CMD_Announcement());
		
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
