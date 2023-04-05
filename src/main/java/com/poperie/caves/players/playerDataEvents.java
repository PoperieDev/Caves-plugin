package com.poperie.caves.players;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class playerDataEvents implements Listener {
    // Implement the onJoin event
    @EventHandler
    private void onJoin(PlayerJoinEvent event) {

        playerMemory memory = new playerMemory();

        File f = new File(playerUtility.getFolderPath(event.getPlayer()) + "/data.yml");

        if (f.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
            memory.setBackPackSize(cfg.getInt("stats.backpackSize"));
            memory.setXp(cfg.getInt("stats.xp"));

            // Backpack
            if (cfg.getList("backpack.items") != null) {
                memory.setBackPack(cfg.getList("backpack.items").toArray(new ItemStack[0]));
            } else {
                memory.setBackPack(null);
            }

        } else {
            // Defaults
            memory.setBackPackSize(5);
            memory.setXp(0);
        }
        playerUtility.setPlayerMemory(event.getPlayer(), memory);
    }


    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        playerMemory memory = playerUtility.getPlayerMemory(event.getPlayer());
        File f = new File(playerUtility.getFolderPath(event.getPlayer()) + "/data.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        // Saved data
        cfg.set("stats.backpackSize", memory.getBackPackSize());
        cfg.set("stats.xp", memory.getXp());
        cfg.set("backpack.items", memory.getBackPack());

        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerUtility.setPlayerMemory(event.getPlayer(), null);
    }
}
