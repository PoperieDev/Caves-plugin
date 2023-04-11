package com.poperie.caves;

import com.poperie.caves.mining.blocks.blockMemory;
import com.poperie.caves.mining.items.itemGroups;
import com.poperie.caves.mining.items.itemMemory;
import com.poperie.caves.mining.items.itemUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class serverData {
    public static void saveServerData() {

        // Blocks
        File f = new File("plugins/caves/blocks.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        // Loop through the locations
        for (Location location : blockMemory.getLocations()) {
            String key = location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
            cfg.set("blocks." + key + ".location", location);
            cfg.set("blocks." + key + ".group", blockMemory.getGroup(location));

        }

        try {
            cfg.save(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadServerData() {

        // Items
        File file = itemUtility.getItemsFile();

        if (!file.exists()) {
            throw new RuntimeException("The items.yml file does not exist!");
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        for (String key : cfg.getConfigurationSection("items").getKeys(false)) {
            System.out.println("Loading item: " + key);

            itemMemory memory = new itemMemory();
            itemUtility.setItemMemory(key, memory);

            memory.setName(cfg.getString("items." + key + ".name"));
            memory.setBlock(cfg.getString("items." + key + ".block"));
            memory.setMaterial(Material.valueOf(cfg.getString("items." + key + ".material")));
            memory.setShiny(cfg.getBoolean("items." + key + ".shiny"));
            memory.setWorth(cfg.getInt("items." + key + ".worth"));
        }
        for (String key : cfg.getConfigurationSection("groups").getKeys(false)) {
            System.out.println("Loading group: " + key);
            itemGroups.addGroup(key, cfg.getStringList("groups." + key + ".items"));
        }


        // Blocks
        // TODO: Create a world loader with a config file
        new WorldCreator("spawn").createWorld();

        File f = new File("plugins/caves/blocks.yml");
        if (f.exists()) {
            YamlConfiguration cfgBlocks = YamlConfiguration.loadConfiguration(f);

            for (String key : cfgBlocks.getConfigurationSection("blocks").getKeys(false)) {
                Location location = (Location) cfgBlocks.get("blocks." + key + ".location");
                blockMemory.addBlock(cfgBlocks.getString("blocks." + key + ".group"), location);
            }
        }
    }
}
