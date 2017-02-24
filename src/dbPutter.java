

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by isaac on 2/21/2017.
 */
public class dbPutter {
    Connection connection;
    String query;

    /**
     * @param connection
     * @param query
     */
    public dbPutter(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    /**
     * @return
     */
    public int put() {
        int id = 0;
        try {
            Statement stmt = connection.createStatement();
            id = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * @return
     */
    public boolean update() {
        boolean success = false;
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


}
