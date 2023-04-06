package com.poperie.caves.items;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class itemUtility {

    // Create a hashmap to store a string and an itemMemory object
    private static final Map<String, itemMemory> itemMemory = new HashMap<>();

    public static itemMemory getItemMemory(String item) {
        if (!itemMemory.containsKey(item)) {
            itemMemory memory = new itemMemory();
            itemMemory.put(item, memory);
            return memory;
        }
        return itemMemory.get(item);
    }

    public static void setItemMemory (String item, itemMemory memory) {
        if (memory == null) {
            itemMemory.remove(item);
        }
        itemMemory.put(item, memory);
    }

    public static File getItemsFile() {
        return new File("plugins/caves/items.yml");
    }

}
