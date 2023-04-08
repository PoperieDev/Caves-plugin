package com.poperie.caves.methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class broadcast {
    public static void broadcastMessage(String messages) {
        for (String message : messages.split("%nl%")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
