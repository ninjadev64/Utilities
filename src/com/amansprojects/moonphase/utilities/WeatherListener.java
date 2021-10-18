package com.amansprojects.moonphase.utilities;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener{
	private final Main plugin;
	private FileConfiguration config;
	public WeatherListener(Main main) {
		this.plugin = main;
		this.config = this.plugin.getConfig();
	}

	@EventHandler(priority=EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {
		if (config.getBoolean("disable weather")) {
			boolean rain = event.toWeatherState();
			if (rain) { event.setCancelled(true); }
		}
    }
 
	@EventHandler(priority=EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {
    	if (config.getBoolean("disable weather")) {
    		boolean storm = event.toThunderState();
        	if (storm) { event.setCancelled(true); }
    	}
    }
}