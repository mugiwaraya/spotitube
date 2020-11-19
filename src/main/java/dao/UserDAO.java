package dao;

import domain.User;
import interfaces.IUser;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUser {
    private Connection conn;
    private final static Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    @Inject
    public UserDAO(DatabaseConnection databaseConnection) {
        this.conn = databaseConnection.getConnection();
        LOGGER.setLevel(Level.WARNING);
    }

    @Override
    public User login(String username, String password){
        User user = null;
        String query = "SELECT id, username, name, email FROM users WHERE username = ? and password = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("name"), rs.getString("email"));
            }
            stm.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A problem has occured with the database:" + e.getMessage());
            throw new RuntimeException("Couldn't connect to database.");
        }
        return user;
    }



}