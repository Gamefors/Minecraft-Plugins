package com.gmf.gmcrnetwork.commands;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping", "", "p");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (commandSender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer) commandSender;
            int ping = p.getPing();
            char color = 'a';
            if(ping > 40){
                color = 'e';
            }
            if(ping > 80){
                color = 'c';
            }
            p.sendMessage(new TextComponent("Your ping is: §" + color + ping + "§fms."));
        }
    }
}

