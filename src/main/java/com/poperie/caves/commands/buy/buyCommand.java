package com.poperie.caves.commands.buy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.poperie.caves.commands.buy.buyInventories.openBuyMenuMain;

public class buyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        openBuyMenuMain(player);
        return true;
    }
}
