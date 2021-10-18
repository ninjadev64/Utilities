package com.amansprojects.moonphase.utilities.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommandListener implements CommandExecutor {
	private final LobbyEventListener lobbyEventListener;
	public LobbyCommandListener(LobbyEventListener lobbyEventListener) {
		this.lobbyEventListener = lobbyEventListener;
	}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            this.lobbyEventListener.sendPlayerToLobby(player);
            return true;
        }
        return false;
    }
}