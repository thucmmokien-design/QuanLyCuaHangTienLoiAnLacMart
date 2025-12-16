package config;
import java.sql.Connection;
import java.sql.DriverManager;

public class datacofig {
    private String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName=AnLacMart;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true";
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }
}
