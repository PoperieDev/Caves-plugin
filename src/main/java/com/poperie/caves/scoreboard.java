package com.poperie.caves;

import com.poperie.caves.players.playerUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import static com.poperie.caves.Caves.getEconomy;
import static com.poperie.caves.methods.formatNum.formatNumber;

public class scoreboard {


    public static void createScoreboard(Player player) {
        // Create a scoreboard
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective(ChatColor.translateAlternateColorCodes('&', "&b&lCaves"), "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        // Add some scores
        setScoreBoardLine(board, " ", 10);

        setScoreBoardLine(board, "&b&lSPILLER &f&lINFO", 9);
        setScoreBoardLine(board, "&8- &fPenge: &b" + getEconomy().format(getEconomy().getBalance(player)), 8);
        // Display backpack size
        setScoreBoardLine(board, "&8- &fRygsæk: &f(&b" + playerUtility.getPlayerMemory(player).getBackPackItemsCount() + "&f/&b" + playerUtility.getPlayerMemory(player).getBackPackSize() + "&f)" , 7);
        setScoreBoardLine(board, "&8- &fXP: &b" + formatNumber((long) playerUtility.getPlayerMemory(player).getXp()), 6);

        player.setScoreboard(board);
    }

    public static void setScoreBoardLine(Scoreboard board, String line, int score) {
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', line)).setScore(score);
    }
}
