import dto.LoginRequestDTO;
import dto.User;
import exceptions.TokenSavingFailedException;
import exceptions.UserNotAuthorizedException;
import interfaces.IAuthTokenDAO;
import interfaces.IUserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.AuthService;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.when;

class AuthServiceTest {

	private static final int STATUS_UNAUTHORIZED = 401;
	private static final int STATUS_FORBIDDEN = 403;
	private static final int STATUS_BADREQUEST = 400;
	private static final int STATUS_OK = 200;
	private static final int STATUS_CREATED = 201;
	private static final String TOKEN = "9d668e63-a275-49ac-b7f1-7eb76f2997f6";

	@Mock
	private static IUserDAO userDAO;

	@Mock
	private static IAuthTokenDAO authTokenDAO;

	private static AuthService authService;

	private LoginRequestDTO loginRequestDTO;
	private User testUser;

	@BeforeEach
	void setupBeforeEach() {
		MockitoAnnotations.initMocks(this);
		loginRequestDTO = new LoginRequestDTO();
		authService = new AuthService();
		authService.setAuthTokenDAO(authTokenDAO);
		authService.setUserDAO(userDAO);
		testUser = new User(1, "TestUser", "Test User", "test@test.nl", TOKEN);
	}

	@Test
	void login() throws UserNotAuthorizedException, TokenSavingFailedException {
		when(userDAO.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())).thenReturn(testUser);
		Response response = authService.login(loginRequestDTO);
		Assertions.assertEquals(STATUS_CREATED, response.getStatus());
	}

	@Test
	void loginWithWrongCredentials() throws UserNotAuthorizedException, TokenSavingFailedException {
		when(userDAO.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())).thenReturn(null);
		Response response = authService.login(loginRequestDTO);
		Assertions.assertEquals(STATUS_UNAUTHORIZED, response.getStatus());
	}
}