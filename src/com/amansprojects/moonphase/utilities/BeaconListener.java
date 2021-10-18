package com.amansprojects.moonphase.utilities;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.BeaconInventory;

public class BeaconListener implements Listener {
    private final Main plugin;
    private FileConfiguration config;
    public BeaconListener(Main main) {
        this.plugin = main;
        this.config = this.plugin.getConfig();
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (config.getBoolean("disable beacons")) {
            if (event.getInventory() instanceof BeaconInventory) { event.setCancelled(true); }
        }
    }
}
