package com.minersleague.main.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.minersleague.main.Main;
import com.minersleague.main.games.codwarfare.Area;
import com.minersleague.main.games.codwarfare.CODGameType;
import com.minersleague.main.games.codwarfare.CODSetupInventory;
import com.minersleague.main.games.codwarfare.CODSpawn;
import com.minersleague.main.games.codwarfare.CODStorage;
import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.util.CODUtils;

public class CMD_COD extends MinersLeagueCommand {

	public CMD_COD() {
		super("cod");
	}

	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		Player p = commandSenderToPlayer(sender);
		if(p.hasPermission("minersleague.rank.developer")) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("save")) {
					File f = new File(Main.plugin.getDataFolder(), "cod_games.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					Area area = CODUtils.getAreaPlayerIsIn(p);
					// Name, Start, End, Lobby, PlayGround
					cfg.set("game."+area.name+".name", area.name);
					cfg.set("game."+area.name+".type", area.type.asString);
					cfg.set("game."+area.name+".lobby.x", area.lobby.getX());
					cfg.set("game."+area.name+".lobby.y", area.lobby.getY());
					cfg.set("game."+area.name+".lobby.z", area.lobby.getZ());
					cfg.set("game."+area.name+".lobby.world", area.lobby.getWorld().getName());
					Location red = area.teamSpawns.get("RED").spawn;
					Location green = area.teamSpawns.get("GREEN").spawn;
					Location blue = area.teamSpawns.get("BLUE").spawn;
					Location uninf = area.teamSpawns.get("UNINFECTED").spawn;
					Location inf = area.teamSpawns.get("INFECTED").spawn;
					cfg.set("game."+area.name+".red.x", red.getX());
					cfg.set("game."+area.name+".red.y", red.getY());
					cfg.set("game."+area.name+".red.z", red.getZ());
					cfg.set("game."+area.name+".green.x", green.getX());
					cfg.set("game."+area.name+".green.y", green.getY());
					cfg.set("game."+area.name+".green.z", green.getZ());
					cfg.set("game."+area.name+".blue.x", blue.getX());
					cfg.set("game."+area.name+".blue.y", blue.getY());
					cfg.set("game."+area.name+".blue.z", blue.getZ());
					cfg.set("game."+area.name+".uninf.x", uninf.getX());
					cfg.set("game."+area.name+".uninf.y", uninf.getY());
					cfg.set("game."+area.name+".uninf.z", uninf.getZ());
					cfg.set("game."+area.name+".inf.x", inf.getX());
					cfg.set("game."+area.name+".inf.y", inf.getY());
					cfg.set("game."+area.name+".inf.z", inf.getZ());
					cfg.set("game."+area.name+".teams.world", blue.getWorld().getName());
					cfg.set("game."+area.name+".ffaSize", area.ffaSpawns.size());
					for(int i = 0; i<area.ffaSpawns.size(); i++) {
						Location ffaSpawn = area.ffaSpawns.get(i).spawn;
						cfg.set("game."+area.name+".ffa"+i+".x", ffaSpawn.getX());
						cfg.set("game."+area.name+".ffa"+i+".y", ffaSpawn.getY());
						cfg.set("game."+area.name+".ffa"+i+".z", ffaSpawn.getZ());
					}
					try {
						cfg.save(f);
					} catch(IOException e) {
						e.printStackTrace();
					}
					messagePlayer(p, "&cSuccessfully saved Game "+area.name+" to File");
					return true;
				}
				if(args[0].equalsIgnoreCase("setup")) {
					if(CODUtils.getPlayerStorage(p)!=null) {
						p.openInventory(CODSetupInventory.setupCodInv());
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("play")) {
					p.setGameMode(GameMode.ADVENTURE);
					p.setExp(0.0f);
					p.setLevel(100);
					String name = CODUtils.gameIn.get(p.getName()).getGameName();
					String team = CODUtils.gameIn.get(p.getName()).team;
					CODUtils.gameIn.put(p.getName(), new CODStorage(name, PlayingStage.PLAYING, team));
					messagePlayer(p, "&cYou started Playing");
					return true;
				}
			}
			if(args.length==2) {
				if(args[0].equalsIgnoreCase("setup")) {
					CODUtils.gameIn.put(p.getName(), new CODStorage(args[1], PlayingStage.IN_LOBBY, null));
					if(CODUtils.getPlayerStorage(p)!=null) {
						p.openInventory(CODSetupInventory.setupCodInv());
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("load")) {
					String loading = args[1];
					File f = new File(Main.plugin.getDataFolder(), "cod_games.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					String name = cfg.getString("game."+loading+".name");
					CODGameType type = CODGameType.getTypeFromString(cfg.getString("game."+loading+".type"));
					Location lobby = new Location(Bukkit.getServer().getWorld(cfg.getString("game."+loading+".lobby.world")), cfg.getDouble("game."+loading+".lobby.x"), cfg.getDouble("game."+loading+".lobby.y"), cfg.getDouble("game."+loading+".lobby.z"));
					World teamWorld = Bukkit.getServer().getWorld(cfg.getString("game."+loading+".teams.world"));
					Location red = new Location(teamWorld, cfg.getDouble("game."+loading+".red.x"), cfg.getDouble("game."+loading+".red.y"), cfg.getDouble("game."+loading+".red.z"));
					Location green = new Location(teamWorld, cfg.getDouble("game."+loading+".green.x"), cfg.getDouble("game."+loading+".green.y"), cfg.getDouble("game."+loading+".green.z"));
					Location blue = new Location(teamWorld, cfg.getDouble("game."+loading+".blue.x"), cfg.getDouble("game."+loading+".blue.y"), cfg.getDouble("game."+loading+".blue.z"));
					Location uninf = new Location(teamWorld, cfg.getDouble("game."+loading+".uninf.x"), cfg.getDouble("game."+loading+".uninf.y"), cfg.getDouble("game."+loading+".uninf.z"));
					Location inf = new Location(teamWorld, cfg.getDouble("game."+loading+".inf.x"), cfg.getDouble("game."+loading+".inf.y"), cfg.getDouble("game."+loading+".inf.z"));
					HashMap<String, CODSpawn> teams = new HashMap<String, CODSpawn>();
					teams.put("RED", new CODSpawn("RED", red));
					teams.put("GREEN", new CODSpawn("GREEN", green));
					teams.put("BLUE", new CODSpawn("BLUE", blue));
					teams.put("UNINFECTED", new CODSpawn("UNINFECTED", uninf));
					teams.put("INFECTED", new CODSpawn("INFECTED", inf));
					ArrayList<CODSpawn> ffaSpawns = new ArrayList<CODSpawn>();
					for(int i = 0; i<cfg.getInt("game."+loading+".ffaSize"); i++) {
						ffaSpawns.add(new CODSpawn("FFA", new Location(teamWorld, cfg.getDouble("game."+loading+".ffa"+i+".x"), cfg.getDouble("game."+loading+".ffa"+i+".y"), cfg.getDouble("game."+loading+".ffa"+i+".z"))));
					}
					CODUtils.areas.put(name, new Area(name, teams, ffaSpawns, lobby, type));
					messagePlayer(p, "&cLoaded COD Game "+name);
					return true;
				}
				if(args[0].equalsIgnoreCase("join")) {
					Area area = CODUtils.areas.get(args[1]);
					if(area.playersIn<9) {
						area.playersIn++;
						CODUtils.gameIn.put(p.getName(), new CODStorage(args[1], PlayingStage.IN_LOBBY, null));
						p.teleport(area.lobby);
						p.openInventory(CODSetupInventory.selectTeam(area));
						return true;
					} else {
						messagePlayer(p, "&cThat Game is full");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("area")) {
					CODUtils.areas.put(args[1], new Area(args[1], new HashMap<String, CODSpawn>(), new ArrayList<CODSpawn>(), null, null));
					messagePlayer(p, "&cYou created Area "+args[1]);
					return true;
				}
			}
		}
		return false;
	}

}
