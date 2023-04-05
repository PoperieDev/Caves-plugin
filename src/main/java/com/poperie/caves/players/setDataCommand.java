package com.poperie.caves.players;

import com.poperie.caves.scoreboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setDataCommand implements CommandExecutor {

    // Command for adding xp to a player
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        playerMemory memory = playerUtility.getPlayerMemory(player);

        // Check if there are no arguments
        if (args.length == 0) {
            player.sendMessage("§cPlease specify a value to change");
            // List all the values that can be changed
            player.sendMessage("§fValues: xp, backpacksize, additemtobackpack and clearbackpack");
            return true;
        }

        if (args[0].equals("xp")) {
            memory.setXp(Double.parseDouble(args[1]));
            player.sendMessage("§aYour new xp is now: " + args[1]);
            scoreboard.createScoreboard(player);
            return true;
        }

        // Adding to backpack
        if (args[0].equals("backpacksize")) {
            memory.setBackPackSize(Integer.parseInt(args[1]));
            player.sendMessage("§aYour new backpack size is now: " + args[1]);
            scoreboard.createScoreboard(player);
            return true;
        }

        // Add item to backpack
        if (args[0].equals("additemtobackpack")) {
            memory.addItemToBackpack(player.getInventory().getItemInHand());
            player.sendMessage("§aAdded item to backpack");
            scoreboard.createScoreboard(player);
            return true;
        }

        // Clear backpack
        if (args[0].equals("clearbackpack")) {
            memory.clearBackPack();
            player.sendMessage("§aCleared backpack");
            scoreboard.createScoreboard(player);
            return true;
        }

        return true;
    }
}
