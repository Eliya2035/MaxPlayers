package me.Eliya.maxPlayers;

import me.Eliya.maxPlayers.Commands.MaxPlayersCMD;
import me.Eliya.maxPlayers.Listeners.PlayerLoginEvent;
import me.Eliya.maxPlayers.Listeners.ServerPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MaxPlayers extends JavaPlugin {

    private static MaxPlayers plugin;
    private static int maxplayers;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

        autoSaveUpdater(getPlugin().getConfig().getBoolean("auto-save"));

        Objects.requireNonNull(getCommand("maxplayers")).setExecutor(new MaxPlayersCMD());
        getServer().getPluginManager().registerEvents(new PlayerLoginEvent(), this);
        getServer().getPluginManager().registerEvents(new ServerPingEvent(), this);

        getLogger().info("MaxPlayers has been enabled!");

    }

    @Override
    public void onDisable() {
        plugin = null;
        getLogger().info("MaxPlayers has been disabled!");
    }

    public static MaxPlayers getPlugin() {
        return plugin;
    }

    public static int getMaxplayers() {
        return maxplayers;
    }

    public static void setMaxplayers(int maxplayers) {
        MaxPlayers.getPlugin().getConfig().set("max-slots", maxplayers);
        MaxPlayers.getPlugin().saveConfig();
        getPlugin().getServer().setMaxPlayers(maxplayers);
        MaxPlayers.maxplayers = maxplayers;
    }

    public void autoSaveUpdater(boolean isOn) {
        if (!isOn) {
            setMaxplayers(20);
            return;
        }
        setMaxplayers(getConfig().getInt("max-slots"));
    }
}
