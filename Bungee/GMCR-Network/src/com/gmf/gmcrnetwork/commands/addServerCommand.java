package com.gmf.gmcrnetwork.commands;

import com.gmf.gmcrnetwork.main.Main;
import com.gmf.gmcrnetwork.objects.ServerInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class addServerCommand extends Command {

    public addServerCommand() {
        super("addServer", "", "as");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (commandSender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer) commandSender;
            if(!p.hasPermission("gmcr.addserver")) return;
            if(strings.length >= 2){
                String serverName = strings[0];
                try {
                    int serverPort = Integer.parseInt(strings[1]);
                    Main.removedServers.remove(serverName);
                    ServerInfo serverInfo = new ServerInfo(serverName, serverPort, false);
                    Main.addServer(serverInfo);
                    p.sendMessage(new TextComponent("Added server: " + serverName + " with port: " + serverInfo.port));
                    Main.serverList.add(serverInfo);
                }catch (Exception ex){
                    p.sendMessage(new TextComponent("§cPort has to be a number."));
                }
            }else{
                p.sendMessage(new TextComponent("§c/addServer [name] [port]"));
            }
        }
    }

}
