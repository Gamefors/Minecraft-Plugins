package com.gmf.gmcrnetwork.commands;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PortBindingsCommand extends Command {

    public PortBindingsCommand(){
        super("portBindings", "", "pb");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        Main.portBindings.forEach((name, address) -> {
            p.sendMessage(new TextComponent(name + ":" + address.getPort()));
        });
    }
}
