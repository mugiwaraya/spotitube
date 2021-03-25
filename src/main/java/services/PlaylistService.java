package services;

import dto.*;
import exceptions.DeleteException;
import exceptions.InsertionException;
import interfaces.IAuthToken;
import interfaces.IPlaylist;
import interfaces.ITrack;
import interfaces.IUser;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;

@Path("playlists")
public class PlaylistService {


	@Inject
	private IPlaylist playlistDAO;

	@Inject
	private IAuthToken authDAO;

	@Inject
	private IUser userDAO;

	@Inject
	private ITrack trackDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlaylists(@QueryParam("token") String token) {
		User user = authDAO.getUserByToken(token);
		Playlists response = playlistDAO.getAllPlaylists(user.getId());
		return Response.ok().entity(response).build();
	}

	@GET
	@Path("/{id}/tracks")
	@Produces("application/json")
	public Response getAllTracksInPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			Tracks response = trackDAO.getTracksInPlaylist(playlistId);
			if (response.getTracks() != null) {
				return Response.ok().entity(response).build();
			}
			return Response.ok().entity(new ArrayList<Track>()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlaylist(Playlist dto, @QueryParam("token") String token) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			AddPlayListDTO playlist = new AddPlayListDTO(dto.getId(), dto.getName(), user.getId(), Collections.emptyList());
			playlistDAO.addPlaylist(playlist);
			Playlists response = playlistDAO.getAllPlaylists(user.getId());
			return Response.ok().entity(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

	@DELETE
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{id}")
	public Response deletePlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			playlistDAO.deletePlaylist(playlistId);
			Playlists response = playlistDAO.getAllPlaylists(user.getId());
			return Response.ok().entity(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(401).build();
		}
	}

	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{id}")
	public Response editPlaylist(Playlist playlist, @QueryParam("token") String token, @PathParam("id") int playlistId) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			playlistDAO.editPlaylist(playlist, playlistId);
			Playlists response = playlistDAO.getAllPlaylists(user.getId());
			return Response.ok().entity(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(401).build();
		}
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{id}/tracks")
	public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, Track dto) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			trackDAO.addTrackToPlaylist(playlistId, dto);
			Tracks response = trackDAO.getTracksInPlaylist(playlistId);
			return Response.ok().entity(response).build();
		} catch (Exception | InsertionException e) {
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

	@DELETE
	@Produces("application/json")
	@Path("/{playlistId}/tracks/{trackId}")
	public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			trackDAO.removeTrackFromPlaylist(playlistId, trackId);
			Tracks response = trackDAO.getTracksInPlaylist(playlistId);
			return Response.ok().entity(response).build();
		} catch (Exception | DeleteException e) {
			e.printStackTrace();
			return Response.status(400).build();
		}
	}
}
