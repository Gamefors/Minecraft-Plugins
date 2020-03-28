package com.gmf.gmfnetwork.commands;

import com.gmf.gmfnetwork.main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (commandSender instanceof ProxiedPlayer) {

            final ProxiedPlayer p = (ProxiedPlayer) commandSender;

            p.sendMessage(new TextComponent(Main.prefix + " Dein Ping ist: §a" +  p.getPing() + "§fms."));

        }
    }
}

