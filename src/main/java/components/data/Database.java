package components.data;

import org.sql2o.Sql2o;

public class Database {
    private static String DB_URL   = "jdbc:postgresql://localhost:5432/site_maintenance";
    private static String username = null;
    private static String password = null;
    public static Sql2o sql2o = new Sql2o(DB_URL,username,password);
}