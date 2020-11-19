package dao;

import javax.ejb.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class DatabaseConnection {
    private static final String filename = "connection.properties";

    Properties props;
    private Connection conn;
    private final static Logger LOGGER = Logger.getLogger("");

    public DatabaseConnection() {}

    private void setupConnection() {
        try {
            props = new Properties();
            InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("connection.properties");
            props.load(input);
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String URL = props.getProperty("url");
            String driver = props.getProperty("driver");
            Class.forName(driver);
            conn = DriverManager.getConnection(URL, username, password);
        }
        catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Couldn't connect to database:" + e.toString());
            throw new RuntimeException("Couldn't connect to database the error message is: " + e.getMessage());
        }

    }



    public Connection getConnection() {
        if(conn != null)
        {
            return conn;
        }
        setupConnection();
        return conn;
    }
}
