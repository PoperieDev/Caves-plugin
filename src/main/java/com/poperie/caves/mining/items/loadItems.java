package com.poperie.caves.mining.items;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class loadItems {
    public static void loadAllItems() {
        // Loop through all items in the resource file
        File file = itemUtility.getItemsFile();

        if (!file.exists()) {
            throw new RuntimeException("The items.yml file does not exist!");
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        // Loop through all the items
        for (String key : cfg.getConfigurationSection("items").getKeys(false)) {
            // Print out the item name
            System.out.println("Loading item: " + key);

            // Create a new itemMemory object
            itemMemory memory = new itemMemory();
            itemUtility.setItemMemory(key, memory);

            memory.setName(cfg.getString("items." + key + ".name"));
            memory.setBlock(cfg.getString("items." + key + ".block"));
            memory.setMaterial(Material.valueOf(cfg.getString("items." + key + ".material")));
            memory.setShiny(cfg.getBoolean("items." + key + ".shiny"));
            memory.setWorth(cfg.getInt("items." + key + ".worth"));
        }
        for (String key : cfg.getConfigurationSection("groups").getKeys(false)) {
            // Print out the item name
            System.out.println("Loading group: " + key);

            itemGroups.addGroup(key, cfg.getStringList("groups." + key + ".items"));
        }
    }
}
