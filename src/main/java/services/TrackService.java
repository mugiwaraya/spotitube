package services;

import dto.UserDTO;
import dto.TracksDTO;
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
			UserDTO userDTO = authDAO.getUserByToken(token);
			if (userDTO == null) {
				return Response.status(403).build();
			}
			TracksDTO result = trackDAO.getAllTracksNotInPlaylist(playlistId);
			return Response.ok().entity(result).build();
		} catch (Exception e){
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

}
