package com.poperie.caves.players;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
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
            memory.setBackPackSlotSize(cfg.getInt("stats.slotSize"));

            if (cfg.getIntegerList("backpack.itemAmounts") != null) {
                List<Integer> itemAmounts = cfg.getIntegerList("backpack.itemAmounts");
                // Loop through the list and add the values to the array
                int[] itemAmountsArray = new int[itemAmounts.size()];
                for (int i = 0; i < itemAmounts.size(); i++) {
                    itemAmountsArray[i] = itemAmounts.get(i);
                }
                memory.setBackPackItemAmounts(itemAmountsArray);
            } else {
                memory.setBackPackItemAmounts(null);
            }

            if (cfg.getList("backpack.items") != null) {
                memory.setBackPack(cfg.getList("backpack.items").toArray(new String[0]));
            } else {
                memory.setBackPack(null);
            }

        } else {
            // Defaults
            memory.setBackPackSize(5);
            memory.setXp(0);
            memory.setBackPackSlotSize(10);
            memory.setBackPack(null);
        }
        playerUtility.setPlayerMemory(event.getPlayer(), memory);
    }


    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        playerData.savePlayerData(event.getPlayer(), true);
    }
}
