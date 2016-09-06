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
import com.minersleague.main.games.towerdefense.Game;
import com.minersleague.main.games.towerdefense.PlayingStage;
import com.minersleague.main.games.towerdefense.TowerDefenseInventory;
import com.minersleague.main.games.towerdefense.TowerDefensePlayerStorage;
import com.minersleague.main.games.towerdefense.mechanics.Animation;
import com.minersleague.main.games.towerdefense.mechanics.Animator;
import com.minersleague.main.games.towerdefense.mechanics.GameStarter;
import com.minersleague.main.games.towerdefense.tower.TowerBuilder;
import com.minersleague.main.games.towerdefense.tower.function.TowerFunction;
import com.minersleague.main.util.Utilities;

public class CMD_GameTowerDefense extends MinersLeagueCommand {

	public CMD_GameTowerDefense() {
		super("towerdefense");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		String msg = null;
		if(p.hasPermission("minersleague.rank.miner")||p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("leave")) {
					if(Utilities.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.IN_LOBBY) {
						Game game = Utilities.games.get(Utilities.gameIn.get(p.getName()));
						game.removePlayer(p.getName());
						Utilities.gameIn.put(p.getName(), new TowerDefensePlayerStorage(p.getName(), PlayingStage.NONE));
						return true;
					}
					if(Utilities.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.NONE) {
						messagePlayer(p, "&cYour not in a Game");
						return true;
					}
					if(Utilities.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.PLAYING) {
						messagePlayer(p, "&cYou cant leave a Runngin Game!");
						return true;
					}
				}
			}
		}
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("leave")) {
					if(Utilities.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.IN_LOBBY) {
						Game game = Utilities.games.get(Utilities.gameIn.get(p.getName()));
						game.removePlayer(p.getName());
						Utilities.gameIn.put(p.getName(), new TowerDefensePlayerStorage(p.getName(), PlayingStage.NONE));
						return true;
					}
					if(Utilities.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.NONE) {
						messagePlayer(p, "&cYour not in a Game");
						return true;
					}
					if(Utilities.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.PLAYING) {
						messagePlayer(p, "&cYou cant leave a Runngin Game!");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("build")) {
					p.openInventory(TowerDefenseInventory.getTowerDefenseInv());
					return true;
				}
			}
			if(args.length==2) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("all")) {
					if(name.equalsIgnoreCase("--all")) {
						Bukkit.getServer().getPluginManager().disablePlugin(Main.plugin);
						Bukkit.getServer().getPluginManager().enablePlugin(Main.plugin);
						messagePlayer(p, "&cYou Reloaded the Plugin");
					} else {
						int active_animators = 0;
						// int inactive_animators = 0;
						for(String id : Utilities.idLink.keySet()) {
							if(id.startsWith(args[1])) {
								Object idLinkObj = Utilities.idLink.get(id);
								if(id.contains("TowerBuilder")) {
									TowerBuilder tb = (TowerBuilder)idLinkObj;
									tb.interrupt();
									messagePlayer(p, "&cYou Stopped "+"&bTowerBuilder: "+id);
								}
								if(id.contains("Animation")) {
									Animation animation = (Animation)idLinkObj;
									animation.stop();
									messagePlayer(p, "&cYou Stopped "+"&dAnimation: "+id);
								}
								if(id.contains("GameStarter")) {
									GameStarter gs = (GameStarter)idLinkObj;
									gs.endGame();
									messagePlayer(p, "&cYou Stopped "+"&aGameMechanic: "+id);
								}
								if(id.contains("Animator")) {
									Animator animator = (Animator)idLinkObj;
									if(!animator.done) {
										active_animators++;
									}
									animator.stop();
								}
								if(id.contains("Thread")) {
									Thread thread = (Thread)idLinkObj;
									if(thread.isAlive()) {
										active_animators++;
									}
									thread.interrupt();
								}
								if(id.contains("TowerFunction")) {
									TowerFunction tf = (TowerFunction)idLinkObj;
									if(tf.repeating) {
										active_animators++;
									}
									tf.repeating = false;
								}
							}
						}
						// msg(p, "&cYou stopped &d"+inactive_animators+" &cInactive and&d "+active_animators+" &cActive Animators (Total: "+(active_animators+inactive_animators)+")"));
						String threadMsg = "&cYou stopped &d"+active_animators+" &cactive Thread";
						if(active_animators==0||active_animators>1) {
							threadMsg = threadMsg+"s";
						}
						messagePlayer(p, threadMsg);
						messagePlayer(p, "&cStopped all Threads with id-Prefix "+args[2]);
						return true;
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("join")) {
					Game game = Utilities.games.get(name);
					ArrayList<String> playersIn = game.getPlayersPlaying();
					playersIn.add(p.getName());
					Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), game.getLobby(), game.getPlayground(), playersIn));
					p.teleport(Utilities.games.get(name).getLobby());
					p.setGameMode(GameMode.CREATIVE);
					messagePlayer(p, "&cYou joined "+name);
					Utilities.gameIn.put(p.getName(), new TowerDefensePlayerStorage(name, PlayingStage.IN_LOBBY));
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
					messagePlayer(p, "&cSuccessfully loaded Game "+name+" from File");
					return true;
				}
				if(args[0].equalsIgnoreCase("save")) {
					if(Utilities.games.keySet().contains(name)&&Utilities.games.get(name)!=null) {
						File f = new File(Main.plugin.getDataFolder(), "games.yml");
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
						Game game = Utilities.games.get(name);
						// Name, Start, End, Lobby, PlayGround
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
						messagePlayer(p, "&cSuccessfully saved Game "+name+" to File");
						return true;
					} else {
						msg = "No Game-Save";
					}
				}
				if(args[0].equalsIgnoreCase("stop")) {
					Utilities.stopAllGameActions(Utilities.games.get(name));
					messagePlayer(p, "&cYou stopped Game "+name);
					return true;
				}
				if(args[0].equalsIgnoreCase("start")) {
					new GameStarter(Utilities.games.get(name));
					messagePlayer(p, "&cSuccessfully started "+name);
					return true;
				}
				if(args[0].equalsIgnoreCase("create")) {
					if(!Utilities.games.keySet().contains(name)) {
						Utilities.games.put(name, new Game(name, null, null, null, null, new ArrayList<String>()));
						messagePlayer(p, "&cCreated Game "+name);
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
						// int inactive_animators = 0;
						for(String id : Utilities.idLink.keySet()) {
							if(id.startsWith(args[2])) {
								Object idLinkObj = Utilities.idLink.get(id);
								if(id.contains("TowerBuilder")) {
									TowerBuilder tb = (TowerBuilder)idLinkObj;
									tb.interrupt();
									messagePlayer(p, "&cYou Stopped "+"&bTowerBuilder: "+id);
								}
								if(id.contains("Animation")) {
									Animation animation = (Animation)idLinkObj;
									animation.stop();
									messagePlayer(p, "&cYou Stopped "+"&dAnimation: "+id);
								}
								if(id.contains("GameStarter")) {
									GameStarter gs = (GameStarter)idLinkObj;
									gs.endGame();
									messagePlayer(p, "&cYou Stopped "+"&aGameMechanic: "+id);
								}
								if(id.contains("Animator")) {
									Animator animator = (Animator)idLinkObj;
									if(!animator.done) {
										active_animators++;
									}
									animator.stop();
								}
								if(id.contains("Thread")) {
									Thread thread = (Thread)idLinkObj;
									if(thread.isAlive()) {
										active_animators++;
									}
									thread.interrupt();
								}
								if(id.contains("TowerFunction")) {
									TowerFunction tf = (TowerFunction)idLinkObj;
									if(tf.repeating) {
										active_animators++;
									}
									tf.repeating = false;
								}
							}
						}
						// msg(p, "&cYou stopped &d"+inactive_animators+" &cInactive and&d "+active_animators+" &cActive Animators (Total: "+(active_animators+inactive_animators)+")"));
						String threadMsg = "&cYou stopped &d"+active_animators+" &cactive Thread";
						if(active_animators==0||active_animators>1) {
							threadMsg = threadMsg+"s";
						}
						messagePlayer(p, threadMsg);
						messagePlayer(p, "&cStopped all Threads with id-Prefix "+args[2]);
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("set")) {
					if(args[2].equalsIgnoreCase("play")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), game.getLobby(), p.getLocation(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Playground was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("lobby")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, game.getStart(), game.getEnd(), p.getLocation(), game.getPlayground(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Lobby was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("end")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, game.getStart(), p.getLocation(), game.getLobby(), game.getPlayground(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Endpoint was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("start")) {
						if(Utilities.games.keySet().contains(name)) {
							Game game = Utilities.games.get(name);
							Utilities.games.put(name, new Game(name, p.getLocation(), game.getEnd(), game.getLobby(), game.getPlayground(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Startpoint was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
				}
			}
		}
		messagePlayer(p, "&cError: "+msg);
		return true;
	}

	public static void messagePlayer(Player p, String msg) {
		p.sendMessage(Utilities.color(msg));
	}
	
}
