package com.poperie.caves.admin;

import com.poperie.caves.players.playerMemory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.poperie.caves.players.playerUtility.getPlayerMemory;

public class coinsAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (args[0] == null) {
            player.sendMessage("§cPlease specify a player");
            return true;
        }

        if (args[1] == null) {
            player.sendMessage("§cPlease specify a value (number)");
            return true;
        }

        if (args[2] == null) {
            player.sendMessage("§cPlease specify a method (add/remove/set)");
            return true;
        }

        if (args[2].equals("add")) {
            int amount = Integer.parseInt(args[1]);
            Player victim = Bukkit.getPlayer(args[0]);
            player.sendMessage("§aAdded " + amount + " coins to " + victim);

            playerMemory victimMemory = getPlayerMemory(victim);
            victimMemory.setCoins(victimMemory.getCoins()+amount);
        } else if (args[2].equals("remove")) {
            int amount = Integer.parseInt(args[1]);
            Player victim = Bukkit.getPlayer(args[0]);
            player.sendMessage("§aRemoved " + amount + " coins from " + victim);

            playerMemory victimMemory = getPlayerMemory(victim);
            victimMemory.setCoins(victimMemory.getCoins()-amount);
        } else if (args[2].equals("set")) {
            int amount = Integer.parseInt(args[1]);
            Player victim = Bukkit.getPlayer(args[0]);
            player.sendMessage("§aSet " + victim + "'s coins to " + amount);

            playerMemory victimMemory = getPlayerMemory(victim);
            victimMemory.setCoins(amount);
        } else {
            player.sendMessage("§cPlease specify a method (add/remove/set)");
        }

        return true;
    }
}
