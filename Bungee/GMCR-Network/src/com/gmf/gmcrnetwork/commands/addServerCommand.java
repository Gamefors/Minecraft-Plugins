package com.gmf.gmcrnetwork.commands;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.net.InetSocketAddress;

public class addServerCommand extends Command {

    public addServerCommand() {
        super("addServer", "", "as");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (commandSender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer) commandSender;
            if(strings.length >= 2){
                String serverName = strings[0];
                try {
                    int port = Integer.parseInt(strings[1]);
                    Main.removedServers.remove(serverName);
                    Main.addServer(serverName, new InetSocketAddress(port));
                    Main.statusOfServers.put(serverName,true);
                    p.sendMessage(new TextComponent("Added server: " + serverName + " with port: " + port));
                }catch (Exception ex){
                    p.sendMessage(new TextComponent("§cPort has to be a number."));
                }
            }else{
                p.sendMessage(new TextComponent("§c/addServer [name] [port]"));
            }
        }
    }

}
