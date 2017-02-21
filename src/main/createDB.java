package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
