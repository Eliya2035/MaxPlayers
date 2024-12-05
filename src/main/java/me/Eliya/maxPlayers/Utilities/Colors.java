package me.Eliya.maxPlayers.Utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Colors {

    public static Component translator(String args) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(args);
    }
}
