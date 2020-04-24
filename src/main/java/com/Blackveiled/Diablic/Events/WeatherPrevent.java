package com.Blackveiled.Diablic.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherPrevent implements Listener {


    @EventHandler
    public void onWeatherChange(WeatherChangeEvent Event) {
        Event.setCancelled(true);
    }
}
