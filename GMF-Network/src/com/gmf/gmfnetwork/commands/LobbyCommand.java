package com.gmf.gmfnetwork.commands;

import com.gmf.gmfnetwork.main.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {

    Main plugin;

    public LobbyCommand(Main main) {
        super("lobby", "", "l","hub");
        this.plugin = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {

        if (commandSender instanceof ProxiedPlayer){

            final ProxiedPlayer p = (ProxiedPlayer) commandSender;

                try {

                    if(p.getServer().getInfo() != this.plugin.getProxy().getServerInfo("lobby")){

                        p.connect(this.plugin.getProxy().getServerInfo("lobby"));

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }
}

