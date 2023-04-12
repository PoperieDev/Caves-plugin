package com.poperie.caves.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reglerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        // Write all the rules to the player
        player.sendMessage("§f ");
        player.sendMessage("§8[ §b§lCAVES §8] §fRegler");
        player.sendMessage("§b1 §fAlle former for hacking er forbudt");
        player.sendMessage("§b2 §fDu må ikke bruge bugs til at få fordele");
        player.sendMessage("§b3 §fDeling af privat information er forbudt");
        player.sendMessage("§b4 §fAlle former for racisme/sexisme/hatred er forbudt");
        player.sendMessage("§b5 §fDu må ikke spamme");
        player.sendMessage("§b6 §fDu må ikke reklamere for andre servere");
        player.sendMessage("§f ");
        player.sendMessage("§bHar du fundet en fejl?");
        player.sendMessage("§fSå kontakt os på Discorden eller i chatten");
        player.sendMessage("§fBelønningen for at finde en server fejl er §b100-5000 coins");
        player.sendMessage("§f ");

        return true;
    }
}
