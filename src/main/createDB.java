package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

/**
 * Created by isaac on 2/21/2017.
 */
public class createDB {
    public createDB() {
        String path = System.getProperty("user.home");
        String url = "jdbc:sqlite:" + path + "/ITdesigns/schools/db/schools.db";
        Path p = Paths.get(path + "/ITdesigns/schools/db");
        File f = new File(path + "/ITdesigns/schools/db");
        f.mkdirs();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver:" + meta.getDriverName());
                System.out.println("A new db has been created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createTables() {

        try {
            Statement stmt = new getDBConnector().getC().createStatement();
            stmt.execute(tablesSQL());
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String tablesSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                " ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " FName TEXT NOT NULL ," +
                " LName TEXT NOT NULL ," +
                " Email VARCHAR(60) NOT NULL UNIQUE" +
                " ); ";
        return sql;
    }
}
