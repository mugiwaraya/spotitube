package dao;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.User;
import exceptions.TokenSavingFailedException;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;
import interfaces.IAuthTokenDAO;
import interfaces.IDatabaseConnection;
import interfaces.IUserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthTokenDAOTest {

	@Mock
	private static IDatabaseConnection databaseConnection;
	@Mock
	private static IUserDAO userDAO;
	@Mock
	private ResultSet rs;
	@Mock
	private PreparedStatement statement;


	private static Connection conn;
	private static IAuthTokenDAO tokenDAO;
	private static AuthRequestDTO authRequestDTO;
	private static AuthResponseDTO authResponseDTO;
	private static User user;

	@BeforeAll
	public static void setupBeforeAll() throws Exception {
		databaseConnection = Mockito.mock(IDatabaseConnection.class);
		conn = Mockito.mock(Connection.class);
		Mockito.when(databaseConnection.getConnection()).thenReturn(conn);
		tokenDAO = new AuthTokenDAO(databaseConnection);
		authResponseDTO = new AuthResponseDTO("Janjansen123", "8cac5d0e-7417-4809-a3a9-11d8bde73f32");
		authRequestDTO = new AuthRequestDTO("Janjansen123", "password123");
		user = new User(1, "Janjansen123", "Jan Jansen", "Janjansen@gmail.com", "8cac5d0e-7417-4809-a3a9-11d8bde73f32");
	}

	@BeforeEach
	void setupBeforeEach() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);
	}

	@Test
	void generateToken() {
		String generatedToken = tokenDAO.generateToken();
		String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";
		boolean matches = generatedToken.matches(regex);
		assertTrue(matches);
	}

	@Test
	void getUserByToken() throws SQLException, UserNotFoundByTokenException, UserNotAuthorizedException {
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);
		Mockito.when(rs.getString("username")).thenReturn(authResponseDTO.getUsername());
		Mockito.when(rs.next()).thenReturn(true, false);
		Mockito.when(statement.executeQuery()).thenReturn(rs);
		User userFromDAO = tokenDAO.getUserByToken(authResponseDTO.getToken());
		Assertions.assertEquals(userFromDAO.getUsername(),authResponseDTO.getUsername());
	}

	@Test
	void getUserByTokenNoUserRetrieved() throws SQLException, UserNotFoundByTokenException {
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);
		Mockito.when(rs.next()).thenReturn(false, false);
		Mockito.when(statement.executeQuery()).thenReturn(rs);
		User userFromDAO = tokenDAO.getUserByToken(authResponseDTO.getToken());
		Assertions.assertEquals(userFromDAO.getUsername(),authResponseDTO.getUsername());
	}

	@Test
	void insertTokenThrowsException() throws SQLException {
		Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);
		Mockito.when(statement.executeUpdate()).thenThrow(new SQLException());
		Assertions.assertThrows(TokenSavingFailedException.class, () -> tokenDAO.insertToken(user, authResponseDTO.getToken()));
	}
}