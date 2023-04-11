package com.poperie.caves.mining.blocks;

import com.poperie.caves.mining.items.itemMemory;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

import static com.poperie.caves.mining.items.itemGroups.getGroup;
import static com.poperie.caves.mining.items.itemUtility.getItemMemory;

public class blockLoader {
    public static void loadBlocks() {
        String[] groups = blockMemory.getGroups();
        Location[] locations = blockMemory.getLocations();

        for (int i = 0; i < groups.length; i++) {
            String group = groups[i];
            Location location = locations[i];

            // Get a random item from the group
            List<String> itemsInGroup = getGroup(group);
            int random = (int) (Math.random() * itemsInGroup.size());
            String item = itemsInGroup.get(random);
            itemMemory itemMemory = getItemMemory(item);
            Block block = location.getBlock();
            block.setType(itemMemory.getBlock());
        }
    }
}
