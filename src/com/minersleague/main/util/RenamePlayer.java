package com.minersleague.main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_10_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.avaje.ebean.text.json.JsonElement;
import com.google.common.collect.Multiset.Entry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.util.UUIDTypeAdapter;

import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.GameProfileSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_10_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerInfo.PlayerInfoData;
import net.minecraft.server.v1_10_R1.PlayerConnection;

public class RenamePlayer {

	private static ExecutorService pool = Executors.newCachedThreadPool();
	private static Field modifiers = getField(Field.class, "modifiers");
	private static Field actionField = getField(PacketPlayOutPlayerInfo.class, "a");
	private static Field dataField = getField(PacketPlayOutPlayerInfo.class, "b");
	private static Field nameField = getField(GameProfile.class, "name");
	private static Field uuidField = getField(GameProfile.class, "id");

	public static void nick(Player player, String name) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					GameProfile prof = GameProfileSerializer.fetch(UUIDFetcher.getUUID(ChatColor.stripColor(name)));
					nameField.set(prof, name);
					uuidField.set(prof, player.getUniqueId());
					EntityPlayer entity = ((CraftPlayer)player).getHandle();
					PacketPlayOutEntityDestroy despawn = new PacketPlayOutEntityDestroy(entity.getId());
					PacketPlayOutPlayerInfo removeProfile = new PacketPlayOutPlayerInfo();
					setInfo(removeProfile, EnumPlayerInfoAction.REMOVE_PLAYER, removeProfile.new PlayerInfoData(entity.getProfile(), -1, null, null));
					PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo();
					setInfo(info, EnumPlayerInfoAction.ADD_PLAYER, info.new PlayerInfoData(prof, entity.ping, entity.playerInteractManager.getGameMode(), CraftChatMessage.fromString(name)[0]));
					PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(entity);
					Set<PlayerConnection> players = Bukkit.getOnlinePlayers().stream().filter(Predicate.isEqual(player).negate()).map(CraftPlayer.class::cast).map(CraftPlayer::getHandle).map(p -> p.playerConnection).collect(Collectors.toSet());
					players.forEach(c -> c.sendPacket(despawn));
					players.forEach(c -> c.sendPacket(removeProfile));
					synchronized(this) {
						wait(125L);
					}
					players.forEach(c -> c.sendPacket(info));
					players.forEach(c -> c.sendPacket(spawn));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static PacketPlayOutPlayerInfo setInfo(PacketPlayOutPlayerInfo packet, EnumPlayerInfoAction action, PlayerInfoData... data) {
		try {
			actionField.set(packet, action);
			dataField.set(packet, Arrays.asList(data));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return packet;
	}

	private static Field getField(Class<?> clazz, String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			if(Modifier.isFinal(field.getModifiers())) {
				modifiers.set(field, field.getModifiers()&~Modifier.FINAL);
			}
			return field;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
