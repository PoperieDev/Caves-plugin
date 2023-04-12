package com.poperie.caves.methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class message {
    public static void broadcastMessage(String messages) {
        for (String message : messages.split("%nl%")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
    public static void messageAllStaff(String messages) {
        for (String message : messages.split("%nl%")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("caves.staff")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                }
            }
        }
    }
}
