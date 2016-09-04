package com.minersleague.main.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.minersleague.main.Main;
import com.minersleague.main.towerdefense.Game;
import com.minersleague.main.towerdefense.TowerDefenseInventory;
import com.minersleague.main.towerdefense.mechanics.Animation;
import com.minersleague.main.towerdefense.mechanics.Animator;
import com.minersleague.main.towerdefense.mechanics.GameStarter;
import com.minersleague.main.towerdefense.tower.TowerBuilder;
import com.minersleague.main.util.Utilities;

public class CMD_Game extends MinersLeagueCommand {
	
	public CMD_Game() {
		super("game");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		String msg = null;
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("build")) {
					p.openInventory(TowerDefenseInventory.getTowerDefenseInv());
//					Tower tower = Towers.towers.get(name);
//					Towers.buildTowner(args[2], 500, tower, p.getLocation());
//					p.sendMessage(Utilities.color("&cTower "+name+" is being created"));
					return true;
				}
			}
			if(args.length==2) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("join")) {
					Game game = Utilities.games.get(name);
					ArrayList<String> playersIn = game.getPlayersPlaying();
					playersIn.add(p.getName());
					Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), game.getLobby(), game.getPlayground(), playersIn));
					p.teleport(Utilities.games.get(name).getLobby());
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage(Utilities.color("&cYou joined "+name));
					Utilities.gameIn.put(p.getName(), name);
					return true;
				}
				if(args[0].equalsIgnoreCase("load")) {
					File f = new File(Main.plugin.getDataFolder(), "games.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					World pointWorld = Bukkit.getServer().getWorld(cfg.getString("game."+name+".points.world"));
					Location start = new Location(pointWorld, cfg.getDouble("game."+name+".start.x"), cfg.getDouble("game."+name+".start.y"), cfg.getDouble("game."+name+".start.z"));
					Location end = new Location(pointWorld, cfg.getDouble("game."+name+".end.x"), cfg.getDouble("game."+name+".end.y"), cfg.getDouble("game."+name+".end.z"));
					Location play = new Location(pointWorld, cfg.getDouble("game."+name+".play.x"), cfg.getDouble("game."+name+".play.y"), cfg.getDouble("game."+name+".play.z"));
					Location lobby = new Location(Bukkit.getServer().getWorld(cfg.getString("game."+name+".lobby.world")), cfg.getDouble("game."+name+".lobby.x"), cfg.getDouble("game."+name+".lobby.y"), cfg.getDouble("game."+name+".lobby.z"));
					Utilities.games.put(name, new Game(name, start, end, lobby, play, new ArrayList<String>()));
					p.sendMessage(Utilities.color("&cSuccessfully loaded Game "+name+" from File"));
					return true;
				}
				if(args[0].equalsIgnoreCase("save")) {
					if(Utilities.games.keySet().contains(name)&&Utilities.games.get(name)!=null) {
						File f = new File(Main.plugin.getDataFolder(), "games.yml");
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
						Game game = Utilities.games.get(name);
						//Name, Start, End, Lobby, PlayGround
						cfg.set("game."+name+".name", name);
						cfg.set("game."+name+".start.x", game.getStart().getX());
						cfg.set("game."+name+".start.y", game.getStart().getY());
						cfg.set("game."+name+".start.z", game.getStart().getZ());
						cfg.set("game."+name+".end.x", game.getEnd().getX());
						cfg.set("game."+name+".end.y", game.getEnd().getY());
						cfg.set("game."+name+".end.z", game.getEnd().getZ());
						cfg.set("game."+name+".play.x", game.getPlayground().getX());
						cfg.set("game."+name+".play.y", game.getPlayground().getY());
						cfg.set("game."+name+".play.z", game.getPlayground().getZ());
						cfg.set("game."+name+".lobby.x", game.getLobby().getX());
						cfg.set("game."+name+".lobby.y", game.getLobby().getY());
						cfg.set("game."+name+".lobby.z", game.getLobby().getZ());
						cfg.set("game."+name+".lobby.world", game.getLobby().getWorld().getName());
						cfg.set("game."+name+".points.world", game.getStart().getWorld().getName());
						try {
							cfg.save(f);
						} catch(IOException e) {
							e.printStackTrace();
						}
						p.sendMessage(Utilities.color("&cSuccessfully saved Game "+name+" to File"));
						return true;
					} else {
						msg = "No Game-Save";
					}
				}
				if(args[0].equalsIgnoreCase("stop")) {
					if(Utilities.gameRunners.keySet().contains(name)&&Utilities.gameRunners.get(name)!=null) {
						GameStarter gs = Utilities.gameRunners.get(name);
						gs.endGame();
						Utilities.gameRunners.put(name, null);
						p.sendMessage(Utilities.color("&cStopped "+name));
						return true;
					} else {
						msg = "No Game Running";
					}
				}
				if(args[0].equalsIgnoreCase("start")) {
					if(Utilities.gameRunners.get(name)!=null) {
						Utilities.gameRunners.get(name).endGame();
						Utilities.gameRunners.put(name, null);
					}
					GameStarter gs = new GameStarter();
					gs.initGameStart(Utilities.games.get(name));
					Utilities.gameRunners.put(name, gs);
					p.sendMessage(Utilities.color("&cSuccessfully started "+name));
					return true;
				}
				if(args[0].equalsIgnoreCase("create")) {
					if(!Utilities.games.keySet().contains(name)) {
						Utilities.games.put(name, new Game(name, null, null, null, null, new ArrayList<String>()));
						p.sendMessage(Utilities.color("&cCreated Game "+name));
						return true;
					} else {
						msg = "Already Exists";
					}
				}
			}
			if(args.length==3) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("stop")) {
					if(name.equalsIgnoreCase("ani")) {
						int active_animators = 0;
						//int inactive_animators = 0;
						for(String id : Utilities.idLink.keySet()) {
							if(id.startsWith(args[2])) {
								Object idLinkObj = Utilities.idLink.get(id);
								String stopped = "";
								if(idLinkObj instanceof GameStarter) {
									GameStarter gs = (GameStarter)idLinkObj;
									gs.endGame();
									stopped = "&aGameStarter: "+id;
									p.sendMessage(Utilities.color("&cYou Stopped "+stopped));
								}
								if(idLinkObj instanceof TowerBuilder) {
									TowerBuilder tb = (TowerBuilder)idLinkObj;
									tb.done = true;
									tb.interrupt();
									stopped = "&bTowerBuilder: "+id;
									p.sendMessage(Utilities.color("&cYou Stopped "+stopped));
								}
								if(idLinkObj instanceof Animation) {
									Animation animation = (Animation)idLinkObj;
									animation.stop();
									stopped = "&dAnimation: "+id;
									p.sendMessage(Utilities.color("&cYou Stopped "+stopped));
								}
								if(idLinkObj instanceof Animator) {
									Animator animator = (Animator)idLinkObj;
									if(!animator.done) {
										active_animators++;
										//inactive_animators++;
									}
									animator.stop();
								}
							}
						}
						//p.sendMessage(Utilities.color("&cYou stopped &d"+inactive_animators+" &cInactive and&d "+active_animators+" &cActive Animators (Total: "+(active_animators+inactive_animators)+")"));
						p.sendMessage(Utilities.color("&cYou stopped &d"+active_animators+" &cactive Animators"));
						p.sendMessage(Utilities.color("&cStopped all Threads with id-Prefix "+args[2]));
						p.sendMessage(Utilities.color("&cNOTE: Inactive Animators are only stopped Animator Objects stored"));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("set")) {
					if(args[2].equalsIgnoreCase("play")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), game.getLobby(), p.getLocation(), game.getPlayersPlaying()));
							p.sendMessage(Utilities.color("&c"+name+"'s Playground was set"));
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("lobby")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), p.getLocation(), game.getPlayground(), game.getPlayersPlaying()));
							p.sendMessage(Utilities.color("&c"+name+"'s Lobby was set"));
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("end")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, game.getStart(), p.getLocation(), game.getLobby(), game.getPlayground(), game.getPlayersPlaying()));
							p.sendMessage(Utilities.color("&c"+name+"'s Endpoint was set"));
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("start")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, p.getLocation(), game.getEnd(), game.getLobby(), game.getPlayground(), game.getPlayersPlaying()));
							p.sendMessage(Utilities.color("&c"+name+"'s Startpoint was set"));
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
				}
			}
		}
		p.sendMessage(Utilities.color("&cError: "+msg));
		return true;
	}

}
