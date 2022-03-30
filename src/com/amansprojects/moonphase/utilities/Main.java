package com.amansprojects.moonphase.utilities;

import com.amansprojects.moonphase.utilities.lobby.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	WeatherListener weatherListener = new WeatherListener(this);
	VoidListener voidListener = new VoidListener(this);
	EnchantingTableListener enchantingTableListener = new EnchantingTableListener(this);
	BeaconListener beaconListener = new BeaconListener(this);
	ConsoleManager consoleManager = new ConsoleManager(this);
	Maintenance maintenance = new Maintenance(this);
	public LobbyEventListener lobbyEventListener;
	public LobbyCommandListener lobbyCommandListener;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(weatherListener, this);
		getServer().getPluginManager().registerEvents(voidListener, this);
		getServer().getPluginManager().registerEvents(enchantingTableListener, this);
		getServer().getPluginManager().registerEvents(beaconListener, this);
		getServer().getPluginManager().registerEvents(new APEnableListener(this), this);
		getServer().getPluginManager().registerEvents(maintenance, this);
		getCommand("maintenance").setExecutor(maintenance);
		getCommand("utilities").setExecutor(new RootCommand(this));
		getServer().getLogger().setFilter(consoleManager);
		getLogger().info("MoonPhase Games: Utilities Plugin Enabled");
	}
	
	@Override public void onDisable() { getLogger().info("MoonPhase Games: Utilities Plugin Disabled"); }

	@Override public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		if (id.equalsIgnoreCase("void")) { return new VoidWorldGenerator(); }
		return null;
	}

	private static class RootCommand implements CommandExecutor {
		private final Main plugin;
		private RootCommand(Main main) {
			this.plugin = main;
		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			assert args.length != 0;
			if (args[0].equalsIgnoreCase("reload")) {
				assert sender.hasPermission("utilities.reload");
				plugin.reloadConfig();
				sender.sendMessage("§7[§lAdmin§r§7] §aSuccessfully reloaded the Utilities configuration!");
				return true;
			}
			return false;
		}
	}
}