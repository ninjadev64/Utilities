package com.amansprojects.moonphase.utilities.lobby;

import com.amansprojects.moonphase.utilities.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public class APEnableListener implements Listener {
    private final Main plugin;
    public APEnableListener(Main main) { this.plugin = main; }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        if (event.getPlugin().getName().equalsIgnoreCase("Additions")) {
            plugin.lobbyEventListener = new LobbyEventListener();
            plugin.lobbyCommandListener = new LobbyCommandListener(plugin.lobbyEventListener);
            plugin.getServer().getPluginManager().registerEvents(plugin.lobbyEventListener, plugin);
            plugin.getCommand("l").setExecutor(plugin.lobbyCommandListener);
        }
    }
}
