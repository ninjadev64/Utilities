package com.amansprojects.moonphase.utilities;

import com.amansprojects.moonphase.utilities.lobby.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	WeatherListener weatherListener = new WeatherListener(this);
	VoidListener voidListener = new VoidListener(this);
	EnchantingTableListener enchantingTableListener = new EnchantingTableListener(this);
	BeaconListener beaconListener = new BeaconListener(this);
	ConsoleManager consoleManager = new ConsoleManager(this);
	LobbyEventListener lobbyEventListener;
	LobbyCommandListener lobbyCommandListener;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		lobbyEventListener = new LobbyEventListener();
		lobbyCommandListener = new LobbyCommandListener(lobbyEventListener);
		getServer().getPluginManager().registerEvents(weatherListener, this);
		getServer().getPluginManager().registerEvents(voidListener, this);
		getServer().getPluginManager().registerEvents(enchantingTableListener, this);
		getServer().getPluginManager().registerEvents(beaconListener, this);
		getServer().getPluginManager().registerEvents(lobbyEventListener, this);
		getCommand("l").setExecutor(lobbyCommandListener);
		getServer().getLogger().setFilter(consoleManager);
		getLogger().info("MoonPhase Games: Utilities Plugin Enabled");
	}
	
	@Override
	public void onDisable() { getLogger().info("MoonPhase Games: Utilities Plugin Disabled"); }
}
