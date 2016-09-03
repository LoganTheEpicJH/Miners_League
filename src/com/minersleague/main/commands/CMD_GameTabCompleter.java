package com.minersleague.main.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.Lists;

public class CMD_GameTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		if(arg1.getName().equalsIgnoreCase("game")) {
			List<String> a1 = Arrays.asList(new String[]{"start", "addpoint", "setup", "endpoint", "startpoint", "build", "setlobby", "playground", "join", "end"});
			Collections.sort(a1);
			List<String> rList = Lists.newArrayList();
			if(args.length==1) {
				for(String s : a1) {
					if(s.toLowerCase().startsWith(args[0])) {
						rList.add(s);
					}
				}
				return rList;
			}
		}
		return null;
	}

}
