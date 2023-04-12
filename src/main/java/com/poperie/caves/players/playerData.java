package com.poperie.caves.players;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class playerData {
    public static void savePlayerData(Player player, boolean deletePlayerMemory) {

        playerMemory memory = playerUtility.getPlayerMemory(player);
        File f = new File(playerUtility.getFolderPath(player) + "/data.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        // Saved data
        cfg.set("stats.backpackSize", memory.getBackPackSize());
        cfg.set("stats.xp", memory.getXp());
        cfg.set("stats.slotSize", memory.getBackPackSlotSize());
        cfg.set("stats.coins", memory.getCoins());
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
}
