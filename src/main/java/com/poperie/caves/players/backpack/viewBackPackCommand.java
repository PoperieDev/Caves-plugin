package com.poperie.caves.players.backpack;

import com.poperie.caves.items.itemMemory;
import com.poperie.caves.items.itemUtility;
import com.poperie.caves.methods.guiMethods;
import com.poperie.caves.players.playerMemory;
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

import static com.poperie.caves.Caves.getEconomy;
import static com.poperie.caves.players.playerUtility.getPlayerMemory;

public class viewBackPackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO: Add more buttons to the backpack inventory (Stats: Stack size, full backpack worth, etc.)

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        playerMemory playerMemory = getPlayerMemory(player);

        // Create a new inventory with 6 rows
        Inventory inventory = player.getServer().createInventory(player, 6 * 9, "§b§lRygsæk");
        guiMethods.fillBottomRow(inventory, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));

        // Fill the backpack with items
        for (int i = 0; i < playerMemory.getBackPackItemsCount(); i++) {
            itemMemory memory = itemUtility.getItemMemory(playerMemory.getBackPackItem(i));

            ItemStack item = memory.getItem(playerMemory.getBackPackItemAmount(i));
            ItemMeta meta = item.getItemMeta();

            List<String> lore = new ArrayList<>();
            lore.add(meta.getLore().get(0));
            System.out.println(i);
            lore.add("§fMængde: §f(§b" + playerMemory.getBackPackItemAmount(i) + "§f/§b" + playerMemory.getBackPackSlotSize() + "§f)");
            lore.add("§bSælg dette item ved salgsboden");
            meta.setLore(lore);
            item.setItemMeta(meta);

            inventory.setItem(i, item);
        }

        // Put a green glass pane named "§7Tom" in the empty slots
        for (int i = playerMemory.getBackPackItemsCount(); i < playerMemory.getBackPackSize(); i++) {
            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§a§lTom");

            List<String> lore = new ArrayList<>();
            lore.add("§7Du har ikke noget item her");
            meta.setLore(lore);

            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }

        // Loop the rest of the slots and set then to red stained glass pane named "§c§lIkke låst op"
        for (int i = playerMemory.getBackPackSize(); i < 5*9; i++) {
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

        // Buttons
        inventory.setItem(6*9-4, guiMethods.getButton("reload", getPlayerMemory(player)));

        ItemStack item = new ItemStack(Material.SIGN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lInfo");

        List<String> lore = new ArrayList<>();
        lore.add("§fStack størrrelse: §b" + playerMemory.getBackPackSlotSize());
        lore.add("§fVærdi: §b" + getEconomy().format(playerMemory.getBackPackWorth()));
        lore.add("§fAntal slots: §b" + playerMemory.getBackPackSize());
        lore.add("§fAntal items: §b" + playerMemory.getBackPackItemsCount());
        meta.setLore(lore);

        item.setItemMeta(meta);
        inventory.setItem(6*9-6, item);

        player.openInventory(inventory);

        return true;

    }
}
