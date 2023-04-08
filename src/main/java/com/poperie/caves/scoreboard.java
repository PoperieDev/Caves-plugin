package com.poperie.caves;

import com.poperie.caves.players.playerUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.poperie.caves.Caves.getEconomy;
import static com.poperie.caves.methods.formatNum.formatNumber;

public class scoreboard {

    static int score;

    public static void createScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective(ChatColor.translateAlternateColorCodes('&', "&b&lCaves"), "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        score = 15;

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");


        setScoreBoardLine(board, "&f          &7" + format.format(now));

        setScoreBoardLine(board, " ");

        setScoreBoardLine(board, "&b&lSPILLER &f&lINFO");
        setScoreBoardLine(board, "&8- &fPenge: &b" + getEconomy().format(getEconomy().getBalance(player)));
        setScoreBoardLine(board, "&8- &fRygsæk: &f(&b" + playerUtility.getPlayerMemory(player).getBackPackItemsCount() + "&f/&b" + playerUtility.getPlayerMemory(player).getBackPackSize() + "&f)");
        setScoreBoardLine(board, "&8- &fXP: &b" + formatNumber((long) playerUtility.getPlayerMemory(player).getXp()));

        setScoreBoardLine(board, "&f");

        setScoreBoardLine(board, "&b&lSERVER &f&lINFO");
        setScoreBoardLine(board, "&8- &fSpillere: &f(&b" + Bukkit.getOnlinePlayers().size() + "&f/&b" + Bukkit.getMaxPlayers()+"&f)");
        setScoreBoardLine(board, "&8- &fVerden: &b" + player.getWorld().getName());
        setScoreBoardLine(board, "&8- &fAktive Events: §cIngen");
        setScoreBoardLine(board, "&f&f");

        player.setScoreboard(board);
    }

    public static void setScoreBoardLine(Scoreboard board, String line) {
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', line)).setScore(score);
        score--;
    }
}
