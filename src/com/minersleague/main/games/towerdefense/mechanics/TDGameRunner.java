package com.minersleague.main.games.towerdefense.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;
import org.spigotmc.AsyncCatcher;

import com.minersleague.main.games.generall.PlayerStorage;
import com.minersleague.main.games.generall.PlayingStage;
import com.minersleague.main.games.generall.SimpleThread;
import com.minersleague.main.games.generall.util.TDUtils;
import com.minersleague.main.games.towerdefense.AdvZombie;
import com.minersleague.main.games.towerdefense.TDGame;
import com.minersleague.main.util.Utilities;

public class TDGameRunner extends SimpleThread {

	public static boolean running;
	public boolean allowed;
	public TDGame game;
	private TDGameRunner gs;
	public TDCountdown c;
	public TDRounds round;
	private LivingEntity lentity;
	public String id;
	public int dead;
	public int zombiesPassed;
	public boolean lost;
	public boolean won;

	public TDGameRunner(TDGame game) {
		AsyncCatcher.enabled = false;
		id = setID(game.getName()+"-GameStarter");
		gs = this;
		this.game = game;
		allowed = false;
		running = true;
		dead = 0;
		zombiesPassed = 0;
		lost = false;
		won = false;
		TDUtils.idLink.put(id, gs);
		startCountdown();
	}
	
	public void startCountdown() {
		//TODO Lobby
		//TDLobby tdl = new TDLobby(game);
		//do {} while(!tdl.done);
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(game.getPlayersPlaying().contains(p.getName())) {
				p.teleport(game.getPlayground());
				p.setGameMode(GameMode.CREATIVE);
				TDUtils.gameIn.put(p.getName(), new PlayerStorage(game.getName(), PlayingStage.PLAYING));
			}
		}
		c = new TDCountdown(gs, 31);
	}
	
	public void startGame() {
		initGameStart();
		System.out.println("Game Started");
		round = new TDRounds(gs);
		for(Entity entity : game.getEnd().getWorld().getNearbyEntities(game.getEnd(), 2d, 2d, 2d)) {
			if(entity instanceof Villager) {
				Villager v = (Villager)entity;
				if(v.getCustomName().equals("End-"+game.getName())) {
					lentity = (LivingEntity)entity;
					break;
				}
			}
		}
		round.nextRound(game, lentity);
		allowed = true;
	}
	
	public void initGameStart() {
		lentity = null;
		executeThread(gs);
		boolean foundVillager = false;
		if(!game.getPlayground().getWorld().getNearbyEntities(game.getEnd(), 2D, 2D, 2D).isEmpty()) {
			for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(game.getEnd(), 2D, 2D, 2D)) {
				if(entity instanceof Villager) {
					foundVillager = true;
					break;
				}
			}
		}
		if(!foundVillager) {
			Villager villager = (Villager)game.getPlayground().getWorld().spawnEntity(game.getEnd(), EntityType.VILLAGER);
			villager.setAdult();
			villager.setProfession(Profession.FARMER);
			villager.setSilent(true);
			villager.setCustomName("End-"+game.getName());
			villager.setCustomNameVisible(true);
			villager.setAI(false);
			foundVillager = true;
		}
	}
	
	public void endGame() {
		running = false;
		for(String s : TDUtils.gameIn.keySet()) {
			if(TDUtils.gameIn.get(s)!=null) {
				if(TDUtils.gameIn.get(s).getGameName().equals(game.getName())) {
					Bukkit.getServer().getPlayer(s).teleport(game.getLobby());
					TDUtils.gameIn.put(s, null);
					TDUtils.gameIn.put(s, new PlayerStorage(game.getName(), PlayingStage.IN_LOBBY));
				}
			}
		}
		for(Entity zombie : game.getPlayground().getWorld().getEntities()) {
			if(zombie instanceof Zombie&&zombie.getCustomName()!=null) {
				if(zombie.getCustomName().equals("Z-"+game.getName())) {
					((Damageable)zombie).setHealth(0.0D);
				}
			}
		}
		for(String up : game.getPlayersPlaying()) {
			TDUtils.gameIn.put(up, null);
		}
		TDUtils.stopAllGameActions(game);
		cancelThread();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(running) {
			if(allowed) {
				//System.out.println(dead+" = "+round.zombiesForRound);
				if(dead==round.zombiesForRound) {
					System.out.println("Next Round Started");
					dead = 0;
					round.nextRound(game, lentity);
					try {
						Thread.sleep(1000);
					} catch(InterruptedException e) {}
				}
				for(Entity entity : game.getPlayground().getWorld().getNearbyEntities(game.getEnd(), 2d, 4d, 2d)) {
					if(entity instanceof Zombie) {
						Zombie zombie = (Zombie)entity;
						//System.out.println("Found Zombie");
						if(zombie.getLocation().getBlockX()==game.getEnd().getBlockX()&&zombie.getLocation().getBlockZ()==game.getEnd().getBlockZ()) {
							zombie.setHealth(0.0D);
							zombiePassed();
							if(lost) {
								TDUtils.stopAllGameActions(game);
							}
							if(won) {
								TDUtils.stopAllGameActions(game);
								for(String s : TDUtils.gameIn.keySet()) {
									if(TDUtils.gameIn.get(s).getGameName().equals(game.getName())) {
										TDUtils.gameIn.put(s, new PlayerStorage(s, PlayingStage.NONE));
										Bukkit.getServer().getPlayer(s).sendTitle(Utilities.color("&a&lGAME WON"), Utilities.color("&c&lYou cleared all Rounds!"));
									}
								}
							}
						}
					}
				}
				for(AdvZombie zombie : round.getZombies()) {
					if(zombie.getSpawn().getTarget()!=lentity) {
						zombie.getSpawn().setTarget(lentity);
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void zombiePassed() {
		zombiesPassed++;
		for(String s : game.joined) {
			Player p = Bukkit.getServer().getPlayer(s);
			if(zombiesPassed==3) {
				p.sendTitle(Utilities.color("&cGAME OVER"), Utilities.color("&aYou lost all Lifes"));
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 0);
				lost = true;
			} else {
				p.sendTitle(Utilities.color("&cA Zombie reached the END!"), Utilities.color("&aYou lost a life &4♥"));
				p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 1, 0);
			}
		}
	}
	
}
