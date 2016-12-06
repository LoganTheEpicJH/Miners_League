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
import com.minersleague.main.games.generall.PlayerStorage;
import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.TDGame;
import com.minersleague.main.games.towerdefense.TowerDefenseInventory;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimation;
import com.minersleague.main.games.towerdefense.mechanics.TDAnimator;
import com.minersleague.main.games.towerdefense.mechanics.TDGameRunner;
import com.minersleague.main.games.towerdefense.tower.TowerBuilder;

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
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.IN_LOBBY) {
						TDGame game = TDUtils.games.get(TDUtils.gameIn.get(p.getName()));
						game.removePlayer(p.getName());
						TDUtils.gameIn.put(p.getName(), new PlayerStorage(p.getName(), PlayingStage.NONE));
						return true;
					}
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.NONE) {
						messagePlayer(p, "&cYour not in a Game");
						return true;
					}
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.PLAYING) {
						messagePlayer(p, "&cYou cant leave a Runngin Game!");
						return true;
					}
				}
			}
		}
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("leave")) {
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.IN_LOBBY) {
						TDGame game = TDUtils.games.get(TDUtils.gameIn.get(p.getName()));
						game.removePlayer(p.getName());
						TDUtils.gameIn.put(p.getName(), new PlayerStorage(p.getName(), PlayingStage.NONE));
						return true;
					}
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.NONE) {
						messagePlayer(p, "&cYour not in a Game");
						return true;
					}
					if(TDUtils.gameIn.get(p.getName()).getPlayingStage()==PlayingStage.PLAYING) {
						messagePlayer(p, "&cYou cant leave a Runngin Game!");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("build")) {
					TDUtils.gameIn.put(p.getName(), new PlayerStorage("game", PlayingStage.NONE));
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
						for(String id : TDUtils.idLink.keySet()) {
							if(id.startsWith(args[1])) {
								Object idLinkObj = TDUtils.idLink.get(id);
								if(id.contains("Animation")) {
									TDAnimation animation = (TDAnimation)idLinkObj;
									animation.stop();
									messagePlayer(p, "&cYou Stopped "+"&dAnimation: "+id);
								}
								if(id.contains("GameStarter")) {
									TDGameRunner gs = (TDGameRunner)idLinkObj;
									gs.endGame();
									messagePlayer(p, "&cYou Stopped "+"&aGameMechanic: "+id);
								}
								if(id.contains("Animator")) {
									TDAnimator animator = (TDAnimator)idLinkObj;
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
								if(id.contains("TowerBuilder")) {
									TowerBuilder tb = (TowerBuilder)idLinkObj;
									tb.interrupt();
									messagePlayer(p, "&cYou Stopped "+"&bTowerBuilder: "+id);
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
					TDGame game = TDUtils.games.get(name);
					ArrayList<String> playersIn = game.getPlayersPlaying();
					playersIn.add(p.getName());
					TDUtils.games.put(name, new TDGame(name, game.getStart(), game.getEnd(), game.getLobby(), game.getPlayground(), playersIn));
					p.teleport(TDUtils.games.get(name).getLobby());
					p.setGameMode(GameMode.CREATIVE);
					messagePlayer(p, "&cYou joined "+name);
					TDUtils.gameIn.put(p.getName(), new PlayerStorage(name, PlayingStage.IN_LOBBY));
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
					TDUtils.games.put(name, new TDGame(name, start, end, lobby, play, new ArrayList<String>()));
					messagePlayer(p, "&cSuccessfully loaded Game "+name+" from File");
					return true;
				}
				if(args[0].equalsIgnoreCase("save")) {
					if(TDUtils.games.keySet().contains(name)&&TDUtils.games.get(name)!=null) {
						File f = new File(Main.plugin.getDataFolder(), "games.yml");
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
						TDGame game = TDUtils.games.get(name);
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
					TDUtils.stopAllGameActions(TDUtils.games.get(name));
					messagePlayer(p, "&cYou stopped Game "+name);
					return true;
				}
				if(args[0].equalsIgnoreCase("start")) {
					new TDGameRunner(TDUtils.games.get(name));
					messagePlayer(p, "&cSuccessfully started "+name);
					return true;
				}
				if(args[0].equalsIgnoreCase("create")) {
					TDUtils.games.put(name, new TDGame(name, null, null, null, null, new ArrayList<String>()));
					messagePlayer(p, "&cCreated Game "+name);
					return true;
				}
			}
			if(args.length==3) {
				String name = args[1];
				if(args[0].equalsIgnoreCase("stop")) {
					if(name.equalsIgnoreCase("ani")) {
						int active_animators = 0;
						// int inactive_animators = 0;
						for(String id : TDUtils.idLink.keySet()) {
							if(id.startsWith(args[2])) {
								Object idLinkObj = TDUtils.idLink.get(id);
								if(id.contains("Animation")) {
									TDAnimation animation = (TDAnimation)idLinkObj;
									animation.stop();
									messagePlayer(p, "&cYou stopped "+"&dAnimation: "+id);
								}
								if(id.contains("GameStarter")) {
									TDGameRunner gs = (TDGameRunner)idLinkObj;
									gs.endGame();
									messagePlayer(p, "&cYou stopped "+"&aGameMechanic: "+id);
								}
								if(id.contains("Animator")) {
									TDAnimator animator = (TDAnimator)idLinkObj;
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
								if(id.contains("TowerBuilder")) {
									TowerBuilder tb = (TowerBuilder)idLinkObj;
									tb.interrupt();
									messagePlayer(p, "&cYou stopped "+"&bTowerBuilder: "+id);
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
						if(TDUtils.games.keySet().contains(name)) {
							TDGame game = TDUtils.games.get(name);
							TDUtils.games.put(name, new TDGame(name, game.getStart(), game.getEnd(), game.getLobby(), p.getLocation(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Playground was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("lobby")) {
						if(TDUtils.games.keySet().contains(name)) {
							TDGame game = TDUtils.games.get(name);
							TDUtils.games.put(name, new TDGame(name, game.getStart(), game.getEnd(), p.getLocation(), game.getPlayground(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Lobby was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("end")) {
						if(TDUtils.games.keySet().contains(name)) {
							TDGame game = TDUtils.games.get(name);
							TDUtils.games.put(name, new TDGame(name, game.getStart(), p.getLocation(), game.getLobby(), game.getPlayground(), game.getPlayersPlaying()));
							messagePlayer(p, "&c"+name+"'s Endpoint was set");
							return true;
						} else {
							msg = "Does not Exist";
						}
					}
					if(args[2].equalsIgnoreCase("start")) {
						if(TDUtils.games.keySet().contains(name)) {
							TDGame game = TDUtils.games.get(name);
							TDUtils.games.put(name, new TDGame(name, p.getLocation(), game.getEnd(), game.getLobby(), game.getPlayground(), game.getPlayersPlaying()));
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

}
