package com.gmf.skyblock.utils;

import com.gmf.skyblock.objects.Island;
import org.bukkit.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MysqlHelper {

    private Connection connection;
    private String host, database, username, password;
    private int port;
    private Statement statement;

    private void setParameters() {
        host = "5.189.129.153";
        port = 3306;
        database = "Skyblock";
        username = "gmf";
        password = "8t7uzer49";
    }

    public MysqlHelper(){
        setParameters();
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database + "?useSSL=false", this.username, this.password);
            statement = connection.createStatement();
        }
    }

    public List<Island> getIslands() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Islands");
        List<Island> islands = new ArrayList<Island>();
        while (result.next()) {
            String id = result.getString("id");
            String owner = result.getString("owner");
            String[] spawn = result.getString("spawn").split(";");
            islands.add(new Island(id, owner, spawn));
        }
        return islands;
    }

    public void addIsland(UUID owner, Location spawn) throws SQLException {
        statement.executeUpdate("INSERT INTO Islands (OWNER, SPAWN) VALUES ('" + owner.toString() + "', '" + spawn.getX() + ";" + spawn.getY() + ";" + spawn.getZ() + "');");
    }

    public Island getIslandByUUID(UUID owner) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM Islands WHERE OWNER = '" + owner.toString() + "'");
        List<Island> islands = new ArrayList<Island>();
        while (result.next()) {
            String id = result.getString("id");
            String owner1 = result.getString("owner");
            String[] spawn = result.getString("spawn").split(";");
            islands.add(new Island(id, owner1, spawn));
        }
        return islands.get(0);
    }

}
