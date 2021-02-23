package io.github.jeremyhu.rpg;

import io.github.jeremyhu.rpg.listeners.ItemListener;
import io.github.jeremyhu.rpg.listeners.PlayerChatListener;
import io.github.jeremyhu.rpg.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FishRPG extends JavaPlugin {

    private static FishRPG plugin;
    private final ItemManager manager = new ItemManager();

    public ItemManager getManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
    }

    public static FishRPG getInstance() {
        return plugin;
    }
}
