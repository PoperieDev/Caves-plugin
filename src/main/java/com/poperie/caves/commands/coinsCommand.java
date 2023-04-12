package com.poperie.caves.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.poperie.caves.players.playerUtility.getPlayerMemory;

public class coinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        player.sendMessage("§f ");
        player.sendMessage("§8[ §b§lCAVES §8] §fCoins");
        player.sendMessage("§fDu har §b" + getPlayerMemory(player).getCoins() + " coins");
        player.sendMessage("§f ");
        return true;
    }
}
