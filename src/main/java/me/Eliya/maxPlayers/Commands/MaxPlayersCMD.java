package me.Eliya.maxPlayers.Commands;

import me.Eliya.maxPlayers.MaxPlayers;
import me.Eliya.maxPlayers.Utilities.Colors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MaxPlayersCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        FileConfiguration config = MaxPlayers.getPlugin().getConfig();

        if (!sender.hasPermission(Objects.requireNonNull(config.getString("Permissions.use")))) {
            sender.sendMessage(Colors.translator(config.getString("Messages.no-permission")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Colors.translator(config.getString("Messages.current-slots").replace("{slots}", String.valueOf(MaxPlayers.getMaxplayers()))));
            return true;
        }

        int slots = 20;

        try {
            slots = Integer.parseInt(args[0]);
        }

        catch (NumberFormatException e) {
            sender.sendMessage(Colors.translator(config.getString("Messages.incorrect-format")));
        }

        if (slots < 0) {
            sender.sendMessage(Colors.translator((String) config.get("Messages.incorrect-format")));
            return true;
        }

        MaxPlayers.setMaxplayers(slots);
        sender.sendMessage(Colors.translator(config.getString("Messages.updated-maxplayers").replace("{slots}", String.valueOf(slots))));

        return true;
    }
}
