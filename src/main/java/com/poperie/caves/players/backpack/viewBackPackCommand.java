package com.poperie.caves.players.backpack;

import com.poperie.caves.items.itemMemory;
import com.poperie.caves.items.itemUtility;
import com.poperie.caves.methods.guiMethods;
import com.poperie.caves.players.playerMemory;
import com.poperie.caves.players.playerUtility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class viewBackPackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Finish this method

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        playerMemory playerMemory = playerUtility.getPlayerMemory(player);

        // Create a new inventory with 6 rows
        Inventory inventory = player.getServer().createInventory(player, 6 * 9, "§b§lRygsæk");
        guiMethods.fillBottomRow(inventory, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));

        // Fill the backpack with items
        for (int i = 0; i < playerMemory.getBackPackItemsCount(); i++) {
            itemMemory memory = itemUtility.getItemMemory(playerMemory.getBackPackItem(i));
            inventory.setItem(i, memory.getItem());
        }

        // Loop the rest of the slots and set then to red stained glass pane named "§c§lIkke låst op"
        for (int i = playerMemory.getBackPackItemsCount(); i < 5*9; i++) {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§c§lIkke låst op");

            List<String> lore = new ArrayList<>();
            lore.add("§7Du har ikke låst denne plads op endnu");
            lore.add("§bOpgrader din ryksæt i butikken");
            meta.setLore(lore);

            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }

        player.openInventory(inventory);

        return true;

    }
}
