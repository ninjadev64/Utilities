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
            this.lobbyEventListener.sendPlayerToLobby((Player) sender, true);
            return true;
        }
        return false;
    }
}