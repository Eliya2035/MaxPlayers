package me.Eliya.maxPlayers;

import me.Eliya.maxPlayers.Commands.MaxPlayersCMD;
import me.Eliya.maxPlayers.Listeners.PlayerLoginEvent;
import me.Eliya.maxPlayers.Listeners.ServerPingEvent;
import me.Eliya.maxPlayers.Utilities.Colors;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MaxPlayers extends JavaPlugin {

    private static MaxPlayers plugin;
    private static int maxplayers;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        if (!getConfig().getBoolean("auto-save"))
            setMaxplayers(20);

        Objects.requireNonNull(getCommand("maxplayers")).setExecutor(new MaxPlayersCMD());
        getServer().getPluginManager().registerEvents(new PlayerLoginEvent(), this);
        getServer().getPluginManager().registerEvents(new ServerPingEvent(), this);

        getLogger().info(String.valueOf(Colors.translator("#ADFC03MaxPlayers has been enabled!")));

    }

    @Override
    public void onDisable() {
        plugin = null;
        getLogger().info(String.valueOf(Colors.translator("#FF3333MaxPlayers has been disabled!")));
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
        MaxPlayers.maxplayers = maxplayers;
    }
}
