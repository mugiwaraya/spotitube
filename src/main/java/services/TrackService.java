package services;

import dto.Tracks;
import dto.User;
import interfaces.IAuthToken;
import interfaces.IPlaylist;
import interfaces.ITrack;
import interfaces.IUser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("tracks")
public class TrackService {
	@Inject
	private IPlaylist playlistDAO;

	@Inject
	private IAuthToken authDAO;

	@Inject
	private IUser userDAO;

	@Inject
	private ITrack trackDAO;

	public TrackService() {
	}

	@GET
	@Produces("application/json")
	public Response getAllTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			Tracks result = trackDAO.getAllTracksNotInPlaylist(playlistId);
			return Response.ok().entity(result).build();
		} catch (Exception e){
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

}
