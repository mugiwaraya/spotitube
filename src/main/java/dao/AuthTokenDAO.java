package dao;

import dto.User;
import exceptions.TokenSavingFailedException;
import exceptions.UserNotFoundByTokenException;
import interfaces.IAuthTokenDAO;
import interfaces.IDatabaseConnection;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthTokenDAO implements IAuthTokenDAO {

	private Connection conn;
	private final static Logger LOGGER = Logger.getLogger(AuthTokenDAO.class.getName());

	@Inject
	public AuthTokenDAO(IDatabaseConnection databaseConnection) throws Exception {
//		this.conn = databaseConnection.getConnection();
		LOGGER.setLevel(Level.WARNING);
	}

	@Override
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public User getUserByToken(String token) throws UserNotFoundByTokenException {
		User user = null;
//		String query = "SELECT id, username, name FROM users where token = ?";
//		try {
//			PreparedStatement statement = conn.prepareStatement(query);
//			statement.setString(1, token);
//
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("name"));
//			}
//			statement.close();
//		} catch (SQLException e) {
//			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error: Cannot connect with the database, " + e);
//		}
//		if (user == null) {
//			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Authorization failed!");
//			throw new UserNotFoundByTokenException();
//		}
		return user;
	}

	@Override
	public boolean insertToken(User user, String token) throws TokenSavingFailedException {
		String query = "UPDATE users SET token = ? WHERE id = ?";
		try {
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, token);
			stm.setInt(2, user.getId());
			stm.executeUpdate();
			stm.close();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "A problem has occured with the database:" + e.getMessage());
			throw new TokenSavingFailedException();
		}
	}
}
