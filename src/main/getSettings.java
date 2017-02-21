package main;

/**
 * Created by isaac on 2/21/2017.
 */
public class getSettings {
    public String getDBSettings() {
        /**
         * @param connection :Contains the host,port,dbname,user,password in the same user.
         *                   they are separated by, :(a colon)
         */
        String db = "127.0.0.1:3306:schools:root::sqlite";
        return db;
    }
}
