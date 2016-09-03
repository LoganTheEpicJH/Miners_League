package com.minersleague.main.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import com.minersleague.main.towerdefense.EndPoint;
import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.towerdefense.GameStarter;
import com.minersleague.main.towerdefense.Point;
import com.minersleague.main.towerdefense.StartPoint;
import com.minersleague.main.towerdefense.Towers;
import com.minersleague.main.util.Utilities;

public class CMD_Test extends MinersLeagueCommand {

	Villager villager;

	public CMD_Test() {
		super("test");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("end")) {
					GameStarter gs = new GameStarter();
					gs.endGame();
					p.sendMessage(Utilities.color("&cGame Endend"));
					return true;
				}
				if(args[0].equalsIgnoreCase("init")) {
					Utilities.games = new HashMap<String, Game>();
					return true;
				}
			}
			if(args.length==2) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("build")) {
					Towers.buildTowner(1000, Towers.towers.get(name), p.getLocation());
					p.sendMessage(Utilities.color("&cU create Tower "+name+"..."));
					return true;
				}
				if(args[0].equalsIgnoreCase("setup")) {
					Utilities.games.put(name, new Game(null, null, null));
					p.sendMessage(Utilities.color("&cGame "+name+" was created!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("sp")) {
					StartPoint sp = new StartPoint(p.getLocation());
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(sp, game.getEnd(), game.getPoints()));
					p.sendMessage(Utilities.color("&cThe Startpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("ep")) {
					EndPoint ep = new EndPoint(p.getLocation());
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(game.getStart(), ep, game.getPoints()));
					p.sendMessage(Utilities.color("&cThe Endpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("ap")) {
					Game game = Utilities.games.get(name);
					ArrayList<Point> points = null;
					if(game.getPoints()==null) {
						points = new ArrayList<Point>();
					} else {
						points = game.getPoints();
					}
					Villager villager = (Villager)p.getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), EntityType.VILLAGER);
					villager.setAI(false);
					villager.setCustomName("Point "+(points.size()+1));
					villager.setCustomNameVisible(true);
					Point point = null;
					if(points.isEmpty()) {
						point = new Point(1, p.getLocation(), villager);
					} else {
						point = new Point(points.get(points.size()-1).getID()+1, p.getLocation(), villager);
					}
					System.out.println(points.size());
					points.add(point);
					Utilities.games.put(name, new Game(game.getStart(), game.getEnd(), points));
					p.sendMessage(Utilities.color("&cYou added Point "+points.size()+" to Game "+name));
					return true;
				}
				if(args[0].equalsIgnoreCase("start")) {
					Game game = Utilities.games.get(name);
					if(game!=null&&game.getStart()!=null&&game.getEnd()!=null&&game.getPoints()!=null) {
						GameStarter gs = new GameStarter();
						gs.startGame(game);
						p.sendMessage(Utilities.color("&cStartet Game"));
						return true;
					}
					p.sendMessage(Utilities.color("&cError starting game: "+game.getStart()+" "+game.getEnd()+" "+game.getPoints()));
					return true;
				}
			}
		}
		return false;
//		if(args.length==1) {
//			if(sender.hasPermission("minersleague.rank.developer")) {
//				if(args[0].equalsIgnoreCase("addv")) {
//					villager = (Villager)((Player)sender).getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), EntityType.VILLAGER);
//					villager.setAI(false);
//					villager.setCustomName("Zombie-Villager");
//					villager.setCustomNameVisible(true);
//					//Towers.buildTowner(500, Towers.blastiodFurnace, ((Player)sender).getLocation());
//					return true;
//				}else if(args[0].equalsIgnoreCase("addz")) {
//					Zombie zombie = (Zombie)((Player)sender).getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), EntityType.ZOMBIE);
//					zombie.setTarget(villager);
//					return true;
//				} else if(isOnlinePlayer(args[0])) {
//					Player target = getOnlinePlayer(args[0]);
//					target.sendMessage(Utilities.color("&6"+sender.getName()+" >> You&f Hi"));
//					sender.sendMessage(Utilities.color("&6You >> "+target.getName()+" &fHi"));
//					return true;
//				} else {
//					sender.sendMessage(Utilities.color("&cThe Player &4"+args[0]+" &cis not Online!"));
//					return true;
//				}
//			} else {
//				sender.sendMessage(Variables.ERROR_PERM_1+": ml.test");
//				return true;
//			}
//		} else {
//			sender.sendMessage(Variables.ERROR_LENGHT_ARGUMENTS);
//			return true;
//		}
//		//return false;
	}

}
