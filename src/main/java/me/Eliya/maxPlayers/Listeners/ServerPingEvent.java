package me.Eliya.maxPlayers.Listeners;

import me.Eliya.maxPlayers.MaxPlayers;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingEvent implements Listener {

    public void onServerPing(ServerListPingEvent event) {
        event.setMaxPlayers(MaxPlayers.getMaxplayers());
    }
}
