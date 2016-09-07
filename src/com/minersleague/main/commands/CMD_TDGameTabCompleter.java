package com.minersleague.main.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.Lists;

public class CMD_TDGameTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		if(arg1.getName().equalsIgnoreCase("game")) {
			List<String> a1 = Arrays.asList(new String[]{"stop", "start", "create", "set"});
			List<String> a3 = Arrays.asList(new String[]{"play", "lobby", "end", "start"});
			Collections.sort(a1);
			Collections.sort(a3);
			if(args.length==1) {
				List<String> rList = Lists.newArrayList();
				for(String s : a1) {
					if(s.toLowerCase().startsWith(args[0])) {
						rList.add(s);
					}
				}
				return rList;
			}
			if(args.length==3) {
				List<String> rList = Lists.newArrayList();
				for(String s : a3) {
					if(s.toLowerCase().startsWith(args[2])) {
						rList.add(s);
					}
				}
				return rList;
			}
		}
		return null;
	}

}
