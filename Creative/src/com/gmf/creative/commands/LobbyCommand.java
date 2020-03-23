package com.gmf.creative.commands;

import com.gmf.creative.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Player;

import java.io.DataOutputStream;
import java.io.IOException;

public class LobbyCommand implements CommandExecutor {

    public Main plugin;

    public LobbyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        String cmdName = cmd.getName();

        if (cmdName.equalsIgnoreCase("lobby") || cmdName.equalsIgnoreCase("l") || cmdName.equalsIgnoreCase("hub")) {

            if (sender != null) {

                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                try {
                    out.writeUTF("Connect");
                    out.writeUTF("lobby");
                } catch (IOException eee) {

                }
                p.sendPluginMessage(this.plugin, "BungeeCord", b.toByteArray());

            }

        }

        return true;

    }
}
