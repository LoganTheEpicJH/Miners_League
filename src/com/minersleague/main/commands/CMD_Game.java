package com.minersleague.main.commands;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.towerdefense.Point;
import com.minersleague.main.towerdefense.mechanics.GameStarter;
import com.minersleague.main.towerdefense.mechanics.PlayingStage;
import com.minersleague.main.towerdefense.tower.Towers;
import com.minersleague.main.util.Utilities;

public class CMD_Game extends MinersLeagueCommand {

	public CMD_Game() {
		super("game");
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
				if(args[0].equalsIgnoreCase("join")) {
					Game game = Utilities.games.get(name);
					p.teleport(game.getLobby());
					Utilities.playing.put(p.getUniqueId(), PlayingStage.IN_LOBBY);
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage(Utilities.color("&cYou joined "+name));
					return true;
				}
				if(args[0].equalsIgnoreCase("playground")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(game.getStart(), game.getEnd(), game.getPoints(), game.getLobby(), p.getLocation()));
					p.sendMessage(Utilities.color("&cYou added "+name+"'s Game Playground"));
					return true;
				}
				if(args[0].equalsIgnoreCase("setlobby")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(game.getStart(), game.getEnd(), game.getPoints(), p.getLocation(), game.getPlayground()));
					p.sendMessage(Utilities.color("&cYou added "+name+"'s Game Lobby"));
					return true;
				}
				if(args[0].equalsIgnoreCase("build")) {
					Towers.buildTowner(1000, Towers.towers.get(name), p.getLocation());
					p.sendMessage(Utilities.color("&cU create Tower "+name+"..."));
					return true;
				}
				if(args[0].equalsIgnoreCase("setup")) {
					if(args[1].equalsIgnoreCase("ho")) {
						ArrayList<Point> points = new ArrayList<Point>();
						org.bukkit.World world = p.getLocation().getWorld();
						Location start = new Location(world, 202, 87, 381);
						Location end = new Location(world, 185, 87, 372);
						points.add(new Point(1, new Location(world, 202, 84, 372)));
						points.add(new Point(2, new Location(world, 185, 84, 372)));
						Location lobby = new Location(world, 210, 87, 377);
						Location playground = new Location(world, 195, 93, 378);
						Game game = new Game(start, end, points, lobby, playground);
						Utilities.games.put("ho", game);
						p.sendMessage(Utilities.color("&cYou created Game ho (PREMADE)"));
						return true;
					}
					if(args[1].equalsIgnoreCase("hi")) {
						if(p.getLocation().getWorld().getName().equalsIgnoreCase("Minigames")) {
							ArrayList<Point> points = new ArrayList<Point>();
							org.bukkit.World world = p.getLocation().getWorld();
							Location start = new Location(world, -192, 17, 57);
							Location end = new Location(world, -164, 17, 36);
							points.add(new Point(1, new Location(world, -186, 14, 57)));
							points.add(new Point(2, new Location(world, -175, 14, 57)));
							points.add(new Point(3, new Location(world, -164, 14, 57)));
							points.add(new Point(4, new Location(world, -164, 14, 51)));
							points.add(new Point(5, new Location(world, -184, 14, 51)));
							points.add(new Point(6, new Location(world, -184, 14, 45)));
							points.add(new Point(7, new Location(world, -188, 14, 45)));
							points.add(new Point(8, new Location(world, -188, 14, 39)));
							points.add(new Point(9, new Location(world, -178, 14, 39)));
							points.add(new Point(10, new Location(world, -178, 14, 45)));
							points.add(new Point(11, new Location(world, -164, 14, 45)));
							points.add(new Point(12, new Location(world, -164, 14, 36)));
							Location joiner = new Location(world, -177, 28, 48);
							Game game = new Game(start, end, points, joiner, joiner);
							Utilities.games.put("hi", game);
							p.sendMessage(Utilities.color("&cYou created Game hi (PREMADE)"));
							return true;
						} else {
							p.sendMessage(Utilities.color("&cYou only can setup hi in World: Minigames"));
							return true;
						}
					} else {
						Utilities.games.put(name, new Game(null, null, null, null, null));
						p.sendMessage(Utilities.color("&cGame "+name+" was created!"));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("startpoint")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(p.getLocation(), game.getEnd(), game.getPoints(), game.getLobby(), game.getPlayground()));
					p.sendMessage(Utilities.color("&cThe Startpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("endpoint")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(game.getStart(), p.getLocation(), game.getPoints(), game.getLobby(), game.getPlayground()));
					p.sendMessage(Utilities.color("&cThe Endpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("addpoint")) {
					Game game = Utilities.games.get(name);
					ArrayList<Point> points = null;
					if(game.getPoints()==null) {
						points = new ArrayList<Point>();
					} else {
						points = game.getPoints();
					}
					Villager villager = (Villager)p.getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), EntityType.VILLAGER);
					villager.setCustomName("Point "+(points.size()+1));
					villager.setCustomNameVisible(true);
					villager.setAdult();
					villager.setProfession(Profession.FARMER);
					villager.setSilent(true);
					villager.setRemoveWhenFarAway(false);
					villager.setAI(false);
					Point point = null;
					if(points.isEmpty()) {
						point = new Point(1, p.getLocation());
					} else {
						point = new Point(points.get(points.size()-1).getID()+1, p.getLocation());
					}
					System.out.println(points.size());
					points.add(point);
					Utilities.games.put(name, new Game(game.getStart(), game.getEnd(), points, game.getLobby(), game.getPlayground()));
					p.sendMessage(Utilities.color("&cYou added Point "+points.size()+" to Game "+name));
					return true;
				}
				if(args[0].equalsIgnoreCase("start")) {
					Game game = Utilities.games.get(name);
					if(game!=null&&game.getStart()!=null&&game.getEnd()!=null&&game.getPoints()!=null) {
						GameStarter gs = new GameStarter();
						gs.initGameStart(game);
						p.sendMessage(Utilities.color("&cStartet Game"));
						return true;
					}
					p.sendMessage(Utilities.color("&cError starting game: "+game.getStart()+" "+game.getEnd()+" "+game.getPoints()));
					return true;
				}
			}
		}
		return false;
	}

}
