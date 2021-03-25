import dto.Playlist;
import dto.User;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;
import interfaces.IAuthTokenDAO;
import interfaces.ITrackDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.TrackService;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.when;

class TrackServiceTest {

	private static final int STATUS_UNAUTHORIZED = 401;
	private static final int STATUS_FORBIDDEN = 403;
	private static final int STATUS_BADREQUEST = 400;
	private static final int STATUS_OK = 200;
	private static final String TOKEN = "9d668e63-a275-49ac-b7f1-7eb76f2997f6";
	private static final int PLAYLISTID = 11;

	@Mock
	private static ITrackDAO trackDAO;

	@Mock
	private static IAuthTokenDAO authDAO;

	private TrackService trackService;
	private User testUser;
	private Playlist playlist;

	@BeforeEach
	void setupBeforeEach() {
		MockitoAnnotations.initMocks(this);
		trackService = new TrackService();
		trackService.setTrackDAO(trackDAO);
		trackService.setAuthDAO(authDAO);
		testUser = new User(1, "TestUser", "Test User", "test@test.nl", TOKEN);
		playlist = new Playlist(PLAYLISTID, "Test Playlist", true, null);
	}

	@Test
	void getAllTracks() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = trackService.getAllTracks(PLAYLISTID, TOKEN);
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void getAllTracksWhenUnauthorized() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotAuthorizedException.class);
		Response response = trackService.getAllTracks(PLAYLISTID, TOKEN);
		Assertions.assertEquals(STATUS_UNAUTHORIZED, response.getStatus());
	}
}