package com.amansprojects.moonphase.utilities;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

public class EnchantingTableListener implements Listener {
	private final Main plugin;
	private FileConfiguration config;
	public EnchantingTableListener(Main main) {
		this.plugin = main;
		this.config = this.plugin.getConfig();
	}

	@EventHandler(priority=EventPriority.HIGHEST)
    public void onClickEvent(InventoryClickEvent event) {
		if (config.getBoolean("instant lapis in enchanting table")) {
	        ItemStack clickedItem = event.getCurrentItem();
	        Player player = (Player) event.getWhoClicked();
	        if (!(player instanceof Player)) { return; }
	        if (event.getInventory().getType() == InventoryType.ENCHANTING) {
	            if (clickedItem.getType().equals(Material.INK_SACK) && clickedItem.getDurability() == 4){
	                event.setCancelled(true);
	            }
	        }
		}
    }

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInventoryClose(InventoryCloseEvent event) {
		if (config.getBoolean("instant lapis in enchanting table")) {
			if (event.getInventory() instanceof EnchantingInventory) {
				EnchantingInventory inventory = (EnchantingInventory) event.getInventory();
				inventory.setItem(1, null);
			}
		}
	}

	@EventHandler(priority=EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent event) {
		if (config.getBoolean("instant lapis in enchanting table")) {
	        if (event.getInventory() instanceof EnchantingInventory) {
	            EnchantingInventory inventory = (EnchantingInventory) event.getInventory();
	            Player player = (Player) event.getPlayer();
	            Dye dye = new Dye();
	            dye.setColor(DyeColor.BLUE);
	            ItemStack item = dye.toItemStack();
	            item.setAmount(64);
	            inventory.setItem(1, item);
	            player.updateInventory();
	        }
		}
    }
}
