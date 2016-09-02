package com.minersleague.main.commands;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import com.minersleague.main.towerdefense.EndPoint;
import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.towerdefense.GameStarter;
import com.minersleague.main.towerdefense.Games;
import com.minersleague.main.towerdefense.Point;
import com.minersleague.main.towerdefense.StartPoint;
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
			}
			if(args.length==2) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("setup")) {
					Games.games.put(name, new Game());
					p.sendMessage(Utilities.color("&cGame "+name+" was created!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("startpoint")) {
					StartPoint sp = new StartPoint(p.getLocation());
					Game game = Games.games.get(name);
					game.setStart(sp);
					Games.games.put(name, game);
					p.sendMessage(Utilities.color("&cThe Startpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("endpoint")) {
					EndPoint ep = new EndPoint(p.getLocation());
					Game game = Games.games.get(name);
					game.setEnd(ep);
					Games.games.put(name, game);
					p.sendMessage(Utilities.color("&cThe Endpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("addpoint")) {
					Game game = Games.games.get(name);
					ArrayList<Point> points = null;
					if(game.getPoints()==null) {
						points = new ArrayList<Point>();
					} else {
						points = game.getPoints();
					}
					Villager villager = (Villager)p.getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), EntityType.VILLAGER);
					villager.setAI(false);
					villager.setCustomName("Point "+points.size()+1);
					villager.setCustomNameVisible(true);
					Point point = new Point(points.size()+1, p.getLocation(), villager);
					points.add(point);
					Games.games.put(name, game);
					p.sendMessage(Utilities.color("&cYou added Point "+points.size()+1+" to Game "+name));
					return true;
				}
				if(args[0].equalsIgnoreCase("start")) {
					Game game = Games.games.get(name);
					if(game.getStart()!=null&&game.getEnd()!=null&&game.getPoints()!=null) {
						GameStarter gs = new GameStarter();
						gs.startGame(game);
						p.sendMessage(Utilities.color("&cStartet Game"));
						return true;
					}
					p.sendMessage(Utilities.color("&cError starting game"));
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
