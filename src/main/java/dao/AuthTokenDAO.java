package dao;

import dto.UserDTO;
import interfaces.IAuthToken;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthTokenDAO implements IAuthToken {

    private Connection conn;
    private final static Logger LOGGER = Logger.getLogger(AuthTokenDAO.class.getName());

    @Inject
    public AuthTokenDAO(DatabaseConnection databaseConnection) {
        this.conn = databaseConnection.getConnection();
    }

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public UserDTO getUserByToken(String token) throws NotAuthorizedException {
        UserDTO userDTO = null;
        String query = "SELECT id, name FROM users where token = ?";


        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, token);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userDTO = new UserDTO(resultSet.getInt("id"), resultSet.getString("name"));
            }
            statement.close();
        } catch ( SQLException e ) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error: Cannot connect with the database, " +  e);
        }

        if (userDTO == null) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Authorization failed!");
            throw new NotAuthorizedException("Unauthorized access");
        }
        return userDTO;
    }

    @Override
    public boolean insertToken(UserDTO userDTO, String token) {
        String query = "UPDATE users SET token = ? WHERE id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, token);
            stm.setInt(2, userDTO.getId());
            stm.executeUpdate();
            stm.close();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "A problem has occured with the database:" + e.getMessage());
        }
        return false;
    }
}
