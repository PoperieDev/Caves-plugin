package com.poperie.caves.mining.blocks;

import org.bukkit.Location;

public class blockMemory {
    static Location[] locations = new Location[0];
    static String[] groups = new String[0];
    //TODO: Implement states
    //TODO: Implement mining events
    /*
     * 0 = Un-mined block
     * 1 = Mined block
     * 2 = Fully mined block
     * States are not saved
     */
    static int[] state = new int[0];

    public static void addBlock(String group, Location location) {
        if (locations == null) {
            locations = new Location[0];
            groups = new String[0];
            state = new int[0];
        }
        Location[] newLocations = new Location[locations.length + 1];
        System.arraycopy(locations, 0, newLocations, 0, locations.length);
        newLocations[locations.length] = location;

        String[] newGroups = new String[groups.length + 1];
        System.arraycopy(groups, 0, newGroups, 0, groups.length);
        newGroups[groups.length] = group;

        // Set the state to 0
        int[] newState = new int[state.length + 1];
        System.arraycopy(state, 0, newState, 0, state.length);
        newState[state.length] = 0;

        locations = newLocations;
        groups = newGroups;
        state = newState;
    }
    public static String getGroup(Location location) {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].equals(location)) {
                return groups[i];
            }
        }
        return null;
    }
    public static String[] getGroups() {
        return groups;
    }
    public static Location[] getLocations() {
        return locations;
    }
    public static int[] getStates() {
        return state;
    }
    public static int getState(Location location) {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].equals(location)) {
                return state[i];
            }
        }
        return -1;
    }

    public static void removeBlock(Location location) {
        if (locations == null) {
            locations = new Location[0];
            groups = new String[0];
        }

        for (int i = 0; i < locations.length; i++) {
            if (locations[i].equals(location)) {
                Location[] newLocations = new Location[locations.length - 1];
                System.arraycopy(locations, 0, newLocations, 0, i);
                System.arraycopy(locations, i + 1, newLocations, i, locations.length - i - 1);
                locations = newLocations;

                String[] newGroups = new String[groups.length - 1];
                System.arraycopy(groups, 0, newGroups, 0, i);
                System.arraycopy(groups, i + 1, newGroups, i, groups.length - i - 1);
                groups = newGroups;

                int[] newState = new int[state.length - 1];
                System.arraycopy(state, 0, newState, 0, i);
                System.arraycopy(state, i + 1, newState, i, state.length - i - 1);
                state = newState;
            }
        }
    }

    public static void setState(Location location, int newState) {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].equals(location)) {
                state[i] = newState;
            }
        }
    }
}
