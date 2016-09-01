package com.minersleague.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.minersleague.main.commands.CMD_Test;
import com.minersleague.main.commands.MinerLeagueCommand;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		/*We dont need this, a Plugin gives a Enabel and Disables Logger Info on its own!
		But there is a simpler way anyway: Bukkit.getServer().getLogger().info(msg);
		PluginDescriptionFile pdfFile = getDescription();
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
	
	public void registerCommand(MinerLeagueCommand cmd) {
		getCommand(cmd.getName()).setExecutor(cmd);
	}
	
}
