package com.poperie.caves.mining.items;

import java.util.HashMap;
import java.util.List;

public class itemGroups {
    static HashMap<String, List<String>> groups = new HashMap<>();

    public static void addGroup(String group, List<String> items) {
        groups.put(group, items);
    }
    public static List<String> getGroup(String group) {
        return groups.get(group);
    }
    public static HashMap<String, List<String>> getGroups() {
        return groups;
    }
}
