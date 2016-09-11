package com.minersleague.main.util;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionbarMessage {

	public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
		return Class.forName("net.minecraft.server."+Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]+"."+nmsClassName);
	}

	public static void showMessage(Player p, String msg) {
		try {
				Object cct = getNmsClass("ChatComponentText").getConstructor(new Class[]{String.class}).newInstance(new Object[]{ChatColor.translateAlternateColorCodes('&', msg)});
				Object ppoc = getNmsClass("PacketPlayOutChat").getConstructor(new Class[]{getNmsClass("IChatBaseComponent"), Byte.TYPE}).newInstance(new Object[]{cct, Byte.valueOf((byte)2)});
				Object craftPlayer = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
				Object playerConnection = craftPlayer.getClass().getField("playerConnection").get(craftPlayer);
				playerConnection.getClass().getMethod("sendPacket", new Class[]{getNmsClass("Packet")}).invoke(playerConnection, new Object[]{ppoc});
		} catch(java.lang.IllegalArgumentException e) {
			e.printStackTrace();
		} catch(InstantiationException e) {
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e) {
			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
}