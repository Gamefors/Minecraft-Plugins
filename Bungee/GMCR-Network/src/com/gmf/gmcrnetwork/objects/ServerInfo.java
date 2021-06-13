package com.gmf.gmcrnetwork.objects;

public class ServerInfo {

    public String name;
    public int port;
    public boolean status;

    public ServerInfo(String name, int port, boolean status) {
        this.name = name;
        this.port = port;
        this.status = status;
    }
}