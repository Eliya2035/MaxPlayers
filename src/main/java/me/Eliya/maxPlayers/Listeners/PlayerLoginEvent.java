package me.Eliya.maxPlayers.Listeners;

import me.Eliya.maxPlayers.MaxPlayers;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class PlayerLoginEvent implements Listener {

    public void onPlayerLogin(org.bukkit.event.player.PlayerLoginEvent event) {
        FileConfiguration config = MaxPlayers.getPlugin().getConfig();

        if (Bukkit.getOnlinePlayers().size() >= MaxPlayers.getMaxplayers() && !event.getPlayer().hasPermission(config.getString("Permissions.bypass").replace("{slots}", String.valueOf(MaxPlayers.getMaxplayers()))))
            event.disallow(org.bukkit.event.player.PlayerLoginEvent.Result.KICK_FULL, config.getString("Messages.full-server-message"));

        else
            event.allow();
    }
}
