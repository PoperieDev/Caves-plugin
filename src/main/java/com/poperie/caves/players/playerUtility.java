package com.poperie.caves.players;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class playerUtility {

    private static final Map<String, playerMemory> playerMemory = new HashMap<>();

    public static playerMemory getPlayerMemory(Player p) {
        if (!playerMemory.containsKey(p.getUniqueId().toString())) {
            playerMemory m = new playerMemory();
            playerMemory.put(p.getUniqueId().toString(), m);
            return m;
        }
        return playerMemory.get(p.getUniqueId().toString());
    }

    public static void setPlayerMemory (Player p, playerMemory memory) {
        if (memory == null) {
            playerMemory.remove(p.getUniqueId().toString());
        }
        playerMemory.put(p.getUniqueId().toString(), memory);
    }

    public static String getFolderPath(Player p) {
        return "plugins/caves/playerData/" + p.getUniqueId();
    }
}
