package main;

import java.sql.*;

/**
 * Created by isaac on 2/21/2017.
 */
public class dbPutter {
    Connection  connection;
    String query;

    public dbPutter(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    public int put(){
        int id=0;
        try {
            Statement stmt=connection.createStatement();
            id=stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public boolean update(){
        boolean success =false;
        try {
            Statement stmt=connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


}
