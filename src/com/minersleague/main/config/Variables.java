package com.minersleague.main.config;

import org.bukkit.configuration.file.YamlConfiguration;

import com.minersleague.main.util.Utilities;

public class Variables {

	YamlConfiguration cfg;
	
	public Variables() {
		/** Since we dont have the Config messages.yml i // it **/
		//cfg = YamlConfiguration.loadConfiguration(new File("plugins/MinersLeague/config/messages.yml"));
	}
	
	/** If we have the Config at some Point we can do:
	public static String NO_PERMISSION_1 = Utilities.color(cfg.getString("msg.NoPermission") **/
	public static String ERROR_PERM_1 = Utilities.color("&cYou dont have the required Permission");
	public static String ERROR_WRONG_ARGUMENTS = Utilities.color("&cWrong Arguments");
	public static String ERROR_LENGHT_ARGUMENTS = Utilities.color("&cCommand is missing or has too many Arguments");
	
}
