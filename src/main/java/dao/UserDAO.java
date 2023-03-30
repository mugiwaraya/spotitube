package dao;

import dto.User;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;
import interfaces.IUserDAO;
import repository.UserRepository;

import javax.inject.Inject;

public class UserDAO implements IUserDAO {


    protected UserRepository userRepository;

    @Inject
    public UserDAO() {
        this.userRepository = new UserRepository();
    }

    @Override
    public User login(String username, String password) throws UserNotAuthorizedException {
        User usr = userRepository.loginUser(username, password);
        if (usr != null) {
            return usr;
        }
        throw new UserNotAuthorizedException("User not authorized");
    }

    @Override
    public boolean authorizedByToken(String token) throws UserNotFoundByTokenException {
        User usr = userRepository.getUserByToken(token);
        if (usr != null) {
            return usr.getToken().equals(token);
        }
        return false;
    }


//	private Connection conn;
//	private final static Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
//
//	@Inject
//	public UserDAO(IDatabaseConnection databaseConnection) throws Exception {
//		this.conn = databaseConnection.getConnection();
//		LOGGER.setLevel(Level.WARNING);
//	}
//
//	@Override
//	public User login(String username, String password) {
//		User user = null;
//		String query = "SELECT id, username, name, email, token FROM users WHERE username = ? and password = ?";
//		try {
//			PreparedStatement stm = conn.prepareStatement(query);
//			stm.setString(1, username);
//			stm.setString(2, password);
//			ResultSet rs = stm.executeQuery();
//
//			while (rs.next()) {
//				user = new User(rs.getInt("id"), rs.getString("username"),
//						rs.getString("name"), rs.getString("email"), rs.getString("token"));
//			}
//			stm.close();
//		} catch (SQLException e) {
//			LOGGER.log(Level.SEVERE, "A problem has occured with the database:" + e.getMessage());
//			throw new RuntimeException("Couldn't connect to database.");
//		}
//		return user;
//	}
//
//
//	public boolean authorizedByToken(String token) throws UserNotFoundByTokenException {
//		String query = "select username from users where token = ?";
//		try (
//				PreparedStatement statement = conn.prepareStatement(query)
//		) {
//			statement.setString(1, token);
//
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new UserNotFoundByTokenException();
//		}
//
//		throw new UserNotFoundByTokenException();
//	}
}