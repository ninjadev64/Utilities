package com.amansprojects.moonphase.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Maintenance implements Listener, CommandExecutor {
    private final Main plugin;
    public Maintenance(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("maintenance")) {
            Player player = event.getPlayer();
            if (!(player.hasPermission("utilities.maintenance.bypass"))) {
                player.kickPlayer(
                        ChatColor.GRAY + "Hi " + ChatColor.BOLD + player.getName() + ChatColor.RESET + ChatColor.GRAY + "!\n\n" +
                                ChatColor.RED + "Unfortunately, we're undergoing maintenance at this time.\n" +
                                ChatColor.GREEN + "We'll be back soon!\n\n" +
                                ChatColor.RESET + ChatColor.BLUE + "Make sure to join our Discord at " +
                                ChatColor.UNDERLINE + "https://discord.gg/NksSvQ373w" + ChatColor.RESET + ChatColor.BLUE + " while you wait!"
                );
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        assert label.equalsIgnoreCase("maintenance");
        if (args.length == 0) {
            boolean current = plugin.getConfig().getBoolean("maintenance");
            if (current) { plugin.getConfig().set("maintenance", false); }
            else { plugin.getConfig().set("maintenance", true); }
        } else if (args[0].equalsIgnoreCase("enable")) {
            plugin.getConfig().set("maintenance", true);
        } else if (args[0].equalsIgnoreCase("disable")) {
            plugin.getConfig().set("maintenance", false);
        } else return false;
        plugin.saveConfig();
        plugin.reloadConfig();
        sender.sendMessage("§7[§lAdmin§r§7] §aUpdated maintenance settings! Maintenance is now set to §l" + plugin.getConfig().getBoolean("maintenance"));
        return true;
    }
}
