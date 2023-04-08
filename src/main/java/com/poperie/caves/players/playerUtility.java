package com.poperie.caves.players;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
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

    public static void savePlayerData(Player player, boolean deletePlayerMemory) {

        playerMemory memory = playerUtility.getPlayerMemory(player);
        File f = new File(playerUtility.getFolderPath(player) + "/data.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        // Saved data
        cfg.set("stats.backpackSize", memory.getBackPackSize());
        cfg.set("stats.xp", memory.getXp());
        cfg.set("stats.slotSize", memory.getBackPackSlotSize());
        cfg.set("backpack.items", memory.getBackPack());
        cfg.set("backpack.itemAmounts", memory.getBackPackItemAmounts());

        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (deletePlayerMemory) {
            playerUtility.setPlayerMemory(player, null);
        }
    }

    public static String getFolderPath(Player p) {
        return "plugins/caves/playerData/" + p.getUniqueId();
    }
}
