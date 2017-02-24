import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by isaac on 2/21/2017.
 */
public class dbGetter {
    String query;
    Connection connection = null;

    /**
     * @param query
     * @param connection
     */
    public dbGetter(String query, Connection connection) {
        this.query = query;
        this.connection = connection;
    }

    /**
     * @return
     */
    public ResultSet getRecords() {
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * @return
     */
    public boolean isRecordsExist() {
        boolean exists = false;
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
