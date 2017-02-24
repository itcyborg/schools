

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by isaac on 2/21/2017.
 */
public class getDBConnector {
    String connection;
    Connection c;

    public getDBConnector() {
        this.connection = getdb();
        /**
         * @param connection :Contains the host,port,dbname,user,password,type in the same user.
         *                   they are separated by, :(a colon)
         */
        String[] temp = connection.split(":");
        String host = temp[0];
        String port = temp[1];
        String dbname = temp[2];
        String user = temp[3];
        String password = temp[4];
        String type = temp[5];
        String path = System.getProperty("user.home");
        String url = "jdbc:sqlite:" + path + "/ITdesigns/schools/db/schools.db";
        String mysqlurl = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
        try {
            Connection conn = null;
            if (type.equals("sqlite")) {
                conn = DriverManager.getConnection(url);
            } else if (type.equals("mysql")) {
                conn = DriverManager.getConnection(mysqlurl, user, password);
            }
            c = conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public Connection getC() {
        return c;
    }

    /**
     * @param c
     */
    public void setC(Connection c) {
        this.c = c;
    }

    /**
     * @return
     */
    public String getdb() {
        return new getSettings().getDBSettings();
    }

    /**
     * @return
     */
    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}