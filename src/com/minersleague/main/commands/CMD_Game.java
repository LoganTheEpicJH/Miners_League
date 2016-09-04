package com.minersleague.main.commands;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.towerdefense.mechanics.GameLogic_2;
import com.minersleague.main.towerdefense.mechanics.GameStarter;
import com.minersleague.main.towerdefense.mechanics.PlayingStage;
import com.minersleague.main.towerdefense.tower.Towers;
import com.minersleague.main.util.Utilities;

public class CMD_Game extends MinersLeagueCommand {

	GameLogic_2 gl2;
	
	public CMD_Game() {
		super("game");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("end-2")) {
					gl2.run = false;
					p.sendMessage(Utilities.color("&cGame Endend"));
					return true;
				}
			}
			if(args.length==2) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("end")) {
					Utilities.gameRunners.get(name).endGame();
					p.sendMessage(Utilities.color("&cGame Endend"));
					return true;
				}
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
					Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), game.getLobby(), p.getLocation()));
					p.sendMessage(Utilities.color("&cYou added "+name+"'s Game Playground"));
					return true;
				}
				if(args[0].equalsIgnoreCase("setlobby")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), p.getLocation(), game.getPlayground()));
					p.sendMessage(Utilities.color("&cYou added "+name+"'s Game Lobby"));
					return true;
				}
				if(args[0].equalsIgnoreCase("build")) {
					Towers.buildTowner(1000, Towers.towers.get(name), p.getLocation());
					p.sendMessage(Utilities.color("&cU create Tower "+name+"..."));
					return true;
				}
				if(args[0].equalsIgnoreCase("setup")) {
//					if(args[1].equalsIgnoreCase("gl2")) {
//						Location start = new Location(p.getLocation().getWorld(), 202.5, 87.1, 380.8);
//						Location end = new Location(p.getLocation().getWorld(), 199, 84, 329);
//						gl2 = new GameLogic_2(start, end);
//						new Thread(gl2).start();
//						p.sendMessage(Utilities.color("&cYou created Game2 gl2 (PREMADE)"));
//						return true;
//					}
					if(args[1].equalsIgnoreCase("ho")) {
						org.bukkit.World world = p.getLocation().getWorld();
						Location start = new Location(world, 202, 87, 381);
						Location end = new Location(world, 185.5, 84.1, 372.5);
						Location lobby = new Location(world, 210, 87, 377);
						Location playground = new Location(world, 195, 93, 378);
						Game game = new Game(name, start, end, lobby, playground);
						Utilities.games.put("ho", game);
						p.sendMessage(Utilities.color("&cYou created Game ho (PREMADE)"));
						return true;
					} else if(args[1].equalsIgnoreCase("hi")) {
						if(p.getLocation().getWorld().getName().equalsIgnoreCase("Minigames")) {
							org.bukkit.World world = p.getLocation().getWorld();
							Location start = new Location(world, -192, 17, 57);
							Location end = new Location(world, -164, 17, 36);
							Location joiner = new Location(world, -177, 28, 48);
							Game game = new Game(name, start, end, joiner, joiner);
							Utilities.games.put("hi", game);
							p.sendMessage(Utilities.color("&cYou created Game hi (PREMADE)"));
							return true;
						} else {
							p.sendMessage(Utilities.color("&cYou only can setup hi in World: Minigames"));
							return true;
						}
					} else {
						Utilities.games.put(name, new Game(name, null, null, null, null));
						p.sendMessage(Utilities.color("&cGame "+name+" was created!"));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("startpoint")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(name, p.getLocation(), game.getEnd(), game.getLobby(), game.getPlayground()));
					p.sendMessage(Utilities.color("&cThe Startpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("endpoint")) {
					Game game = Utilities.games.get(name);
					Utilities.games.put(name, new Game(name, game.getStart(), p.getLocation(), game.getLobby(), game.getPlayground()));
					Villager v = (Villager)p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
					v.setProfession(Profession.FARMER);
					v.setAdult();
					v.setSilent(true);
					v.setCustomName("End-"+game.getName());
					v.setCustomNameVisible(true);
					v.setAI(false);
					p.sendMessage(Utilities.color("&cThe Endpoint from Game "+name+" was set!"));
					return true;
				}
				if(args[0].equalsIgnoreCase("start")) {
					Game game = Utilities.games.get(name);
					if(game.noNull()) {
						GameStarter gs = new GameStarter();
						gs.initGameStart(game);
						Utilities.gameRunners.put(name, gs);
						p.sendMessage(Utilities.color("&cStartet Game"));
						return true;
					}
					p.sendMessage(Utilities.color("&cError starting game"));
					return true;
				}
			}
		}
		return false;
	}

}
