package com.poperie.caves.admin;

import com.poperie.caves.mining.items.itemMemory;
import com.poperie.caves.mining.items.itemUtility;
import com.poperie.caves.players.playerMemory;
import com.poperie.caves.players.playerUtility;
import com.poperie.caves.scoreboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

import static com.poperie.caves.mining.items.itemGroups.getGroups;


public class dataCommand implements CommandExecutor {

    // Command for adding xp to a player
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        playerMemory playerMemory = playerUtility.getPlayerMemory(player);

        // Check if there are no arguments
        if (args.length == 0) {
            player.sendMessage("§cPlease specify a value to change");
            // List all the values that can be changed
            player.sendMessage("§fValues: xp, backpacksize, additemtobackpack, getitemdata, clearbackpack, viewgroups");
            return true;
        }

        if (args[0].equals("viewgroups")) {
            HashMap<String, List<String>> groups = getGroups();
            for (String group : groups.keySet()) {
                player.sendMessage("§fGroup: " + group);
                player.sendMessage("§fItems: " + groups.get(group));
            }
            return true;
        }

        if (args[0].equals("xp")) {
            playerMemory.setXp(Double.parseDouble(args[1]));
            player.sendMessage("§aYour new xp is now: " + args[1]);
            scoreboard.createScoreboard(player);
            return true;
        }

        // Adding to backpack
        if (args[0].equals("backpacksize")) {
            playerMemory.setBackPackSize(Integer.parseInt(args[1]));
            player.sendMessage("§aYour new backpack size is now: " + args[1]);
            scoreboard.createScoreboard(player);
            return true;
        }

        // Add item to backpack
        if (args[0].equals("additemtobackpack")) {
            playerMemory.addItemToBackpack(args[1]);
            player.sendMessage("§aAdded item to backpack");
            scoreboard.createScoreboard(player);
            return true;
        }

        // Clear backpack
        if (args[0].equals("clearbackpack")) {
            playerMemory.clearBackPack();
            player.sendMessage("§aCleared backpack");
            scoreboard.createScoreboard(player);
            return true;
        }

        // Get item data
        if (args[0].equals("getitemdata")) {
            itemMemory memory = itemUtility.getItemMemory(args[1]);
            player.sendMessage("§fName: §b" + memory.getName());
            player.sendMessage("§fMaterial: §b" + memory.getMaterial());
            player.sendMessage("§fShiny: §b" + memory.getShiny());
            player.sendMessage("§fWorth: §b" + memory.getWorth());

            return true;
        }

        // Give item
        if (args[0].equals("giveitem")) {
            itemMemory memory = itemUtility.getItemMemory(args[1]);
            player.getInventory().addItem(memory.getItem(1));
            return true;
        }

        return true;
    }
}
