package com.amansprojects.moonphase.utilities;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidListener implements Listener {
	private final Main plugin;
	private FileConfiguration config;
	public VoidListener(Main main) {
		this.plugin = main;
		this.config = this.plugin.getConfig();
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent event) {
		if (config.getBoolean("instant void death")) {
			Player player = event.getPlayer();
			if (player.getLocation().getY() <= -25){
				player.setHealth(0);
				player.spigot().respawn();
			}
		}
	}
}