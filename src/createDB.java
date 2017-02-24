

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
                meta.getDriverName();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public boolean createTables() {

        try {
            Statement stmt = new getDBConnector().getC().createStatement();
            stmt.execute(tablesSQL());
            stmt.execute(tablesSQL1());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @return
     */
    private String tablesSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                " ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " ADM TEXT NO NULL UNIQUE, " +
                " FName TEXT NOT NULL ," +
                " LName TEXT NOT NULL ," +
                " Email VARCHAR(60) NOT NULL UNIQUE" +
                " );";
        return sql;
    }

    /**
     * @return
     */
    private String tablesSQL1() {
        return "CREATE TABLE IF NOT EXISTS fees (" +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "ADM TEXT NOT NULL ," +
                "AMOUNT INTEGER NOT NULL," +
                "PAID INTEGER NOT NULL," +
                "UPDATED text NOT NULL" +
                ");";
    }

}
