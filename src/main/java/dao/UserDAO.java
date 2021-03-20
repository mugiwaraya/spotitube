package dao;

import dto.UserDTO;
import exceptions.UserNotFoundByTokenException;
import interfaces.IUser;

import javax.inject.Inject;
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
	public UserDTO login(String username, String password) {
		UserDTO userDTO = null;
		String query = "SELECT id, username, name, email, token FROM users WHERE username = ? and password = ?";
		try {
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, username);
			stm.setString(2, password);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				userDTO = new UserDTO(rs.getInt("id"), rs.getString("username"),
						rs.getString("name"), rs.getString("email"), rs.getString("token"));
			}
			stm.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "A problem has occured with the database:" + e.getMessage());
 			throw new RuntimeException("Couldn't connect to database.");
		}
		return userDTO;
	}

	public String getUserByToken(String token) throws UserNotFoundByTokenException {
		String query = "select username from users where token = ?";
		try (
				PreparedStatement statement = conn.prepareStatement(query)
		) {
			statement.setString(1, token);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				return rs.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new UserNotFoundByTokenException();
		}

		throw new UserNotFoundByTokenException();
	}
}