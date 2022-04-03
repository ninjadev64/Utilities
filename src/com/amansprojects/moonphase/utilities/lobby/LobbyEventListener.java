package com.amansprojects.moonphase.utilities.lobby;

import net.gcnt.additionsplus.api.AdditionsAPI;
import net.gcnt.additionsplus.api.AdditionsPlugin;
import net.gcnt.additionsplus.api.objects.AdditionsBook;
import net.gcnt.additionsplus.api.objects.AdditionsItem;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LobbyEventListener implements Listener {

    AdditionsAPI additionsAPI = ((AdditionsPlugin) Bukkit.getPluginManager().getPlugin("Additions")).getAPI();

	public void sendPlayerToLobby(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        AdditionsBook helpBook = additionsAPI.getBookByName("Help");
        AdditionsItem gameSelectorItem = additionsAPI.getItemByName("Games");
        player.setGameMode(GameMode.ADVENTURE);
		player.teleport(new Location(Bukkit.getWorld("Lobby"), 0, 7, 0));
        for (PotionEffect effect : player.getActivePotionEffects()) { player.removePotionEffect(effect.getType()); }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 1));
        player.setHealth(20);
        helpBook.giveBook(player);
        gameSelectorItem.giveItem(player, true);
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        sendPlayerToLobby(player);
        event.setJoinMessage("§9" + player.getDisplayName() + "§r §7landed on the lobby!");
    }
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
        if (event.getPlayer().getLocation().getWorld().toString().equalsIgnoreCase("Lobby")) {
        	Player player = event.getPlayer();
            sendPlayerToLobby(player);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        String worldName = event.getEntity().getWorld().getName();
        if (worldName.equalsIgnoreCase("Lobby") || worldName.equalsIgnoreCase("SkyWars")) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }
}
