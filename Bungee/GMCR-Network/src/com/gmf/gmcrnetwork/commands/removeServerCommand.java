package com.gmf.gmcrnetwork.commands;

import com.gmf.gmcrnetwork.main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class removeServerCommand extends Command {

    public removeServerCommand(){
        super("removeServer", "", "rs");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if (commandSender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer) commandSender;
            if(!p.hasPermission("gmcr.removeserver")) return;
            if(strings.length >= 1){
                String serverName = strings[0];
                Main.removedServers.add(serverName);
                Main.removeServer(serverName, p.getServer());

                //TODO: this throws error because of removing while iterating
                Main.serverList.forEach(serverInfo -> {
                    if(serverInfo.name.equals(serverName)){
                        Main.serverList.remove(serverInfo);
                    }
                });
                p.sendMessage(new TextComponent("Removed server: " + serverName));
            }else{
                p.sendMessage(new TextComponent("§c/removeServer [name]"));
            }
        }
    }

}
