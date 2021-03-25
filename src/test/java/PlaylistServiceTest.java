import dto.Playlist;
import dto.Track;
import dto.User;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;
import interfaces.IAuthTokenDAO;
import interfaces.IPlaylistDAO;
import interfaces.ITrackDAO;
import interfaces.IUserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.PlaylistService;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.when;

class PlaylistServiceTest {

	private static final int STATUS_BADREQUEST = 400;
	private static final int STATUS_OK = 200;
	private static final int TRACKID = 55;
	private static final int PLAYLISTID = 11;
	private static final String TOKEN = "9d668e63-a275-49ac-b7f1-7eb76f2997f6";


	private static Playlist playlist;
	private static Track track;

	@Mock
	private static IPlaylistDAO playlistDAO;
	@Mock
	private static ITrackDAO trackDAO;
	@Mock
	private static IAuthTokenDAO authDAO;
	@Mock
	private static IUserDAO userDAO;

	private User testUser;

	private static PlaylistService playlistService;

	@BeforeEach
	void beforeEachSetup() {
		MockitoAnnotations.initMocks(this);
		playlistService = new PlaylistService();
		playlistService.setPlaylistDAO(playlistDAO);
		playlistService.setAuthDAO(authDAO);
		playlistService.setUserDAO(userDAO);
		playlistService.setTrackDAO(trackDAO);
		testUser = new User(1, "TestUser", "Test User", "test@test.nl", TOKEN);
		playlist = new Playlist(PLAYLISTID, "Test Playlist", true, null);
		track = new Track();
	}

	@Test
	void getAllPlaylists() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = playlistService.getAllPlaylists(TOKEN);
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void getAllPlaylistsWhenUnauthorized() throws  UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.getAllPlaylists(TOKEN);
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}

	@Test
	void getAllTracksInPlaylist() throws UserNotFoundByTokenException  {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.getAllTracksInPlaylist(1, TOKEN);
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}

	@Test
	void addPlaylist() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = playlistService.addPlaylist(playlist, TOKEN);
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void addPlaylistWhenUnauthorized() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.addPlaylist(playlist, TOKEN);
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}

	@Test
	void deletePlaylist() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = playlistService.deletePlaylist(PLAYLISTID, TOKEN);
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void deletePlaylistWhenUnauthorized() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.deletePlaylist(PLAYLISTID, TOKEN);
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}


	@Test
	void editPlaylist() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = playlistService.editPlaylist(playlist, TOKEN, PLAYLISTID);
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void editPlaylistWhenUnautorized() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.editPlaylist(playlist, TOKEN, PLAYLISTID);
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}


	@Test
	void addTrackToPlaylist() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = playlistService.addTrackToPlaylist(TOKEN, 11, track);
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void addTrackToPlaylistWhenUnauthorized() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.addTrackToPlaylist(TOKEN, 11, track);
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}

	@Test
	void deleteTrackFromPlaylist() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenReturn(testUser);
		Response response = playlistService.deleteTrackFromPlaylist(TOKEN, 11, track.getId());
		Assertions.assertEquals(STATUS_OK, response.getStatus());
	}

	@Test
	void deleteTrackFromPlaylistWhenUnauthorized() throws UserNotFoundByTokenException {
		when(authDAO.getUserByToken(TOKEN)).thenThrow(UserNotFoundByTokenException.class);
		Response response = playlistService.deleteTrackFromPlaylist(TOKEN, 11, track.getId());
		Assertions.assertEquals(STATUS_BADREQUEST, response.getStatus());
	}

}