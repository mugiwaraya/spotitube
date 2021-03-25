package services;

import dto.Tracks;
import dto.User;
import exceptions.UserNotAuthorizedException;
import interfaces.IAuthTokenDAO;
import interfaces.ITrackDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackService {

	private IAuthTokenDAO authDAO;

	private ITrackDAO trackDAO;

	public TrackService() {
	}

	@Inject
	public void setAuthDAO(IAuthTokenDAO authDAO) {
		this.authDAO = authDAO;
	}

	@Inject
	public void setTrackDAO(ITrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}

	@GET
	@Produces("application/json")
	public Response getAllTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
		try {
			User user = authDAO.getUserByToken(token);
			Tracks result = trackDAO.getAllTracksNotInPlaylist(playlistId);
			return Response.ok().entity(result).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}


}
