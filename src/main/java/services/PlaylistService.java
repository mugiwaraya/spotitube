package services;

import domain.Playlist;
import domain.Track;
import domain.User;
import dto.playlist.AddPlaylistRequestDTO;
import dto.playlist.AddPlaylistResponseDTO;
import dto.playlist.GetAllPlaylistsResponseDTO;
import dto.playlist.GetTracksInPlaylistDTO;
import interfaces.IAuthToken;
import interfaces.IPlaylist;
import interfaces.ITrack;
import interfaces.IUser;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
		ArrayList<Playlist> playlists = (ArrayList<Playlist>) playlistDAO.getAllPlaylists(user.getId());
		for (Playlist playlist : playlists) {
			playlist.setTracks((ArrayList<Track>) trackDAO.getTracksInPlaylist(playlist.getId()));
		}
		GetAllPlaylistsResponseDTO result = new GetAllPlaylistsResponseDTO(playlists);
		return Response.ok().entity(result).build();
	}


	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlaylist(AddPlaylistRequestDTO dto, @QueryParam("token") String token) {
		try {
			User user = authDAO.getUserByToken(token);
			if (user == null) {
				return Response.status(403).build();
			}
			Playlist playList = new Playlist(0, dto.getName(), user.getId());
			playlistDAO.addPlaylist(playList);
			List<Playlist> userPlaylists = playlistDAO.getAllPlaylists(user.getId());
			int length = 0;
			for (Playlist pl : userPlaylists) {
				length += pl.getLength();
			}
			AddPlaylistResponseDTO response = new AddPlaylistResponseDTO(userPlaylists, length);
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
			GetAllPlaylistsResponseDTO result = new GetAllPlaylistsResponseDTO(playlistDAO.getAllPlaylists(authDAO.getUserByToken(token).getId()));
			return Response.ok().entity(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(401).build();
		}
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
			GetTracksInPlaylistDTO result = new GetTracksInPlaylistDTO(trackDAO.getTracksInPlaylist(playlistId));
			return Response.ok().entity(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

}
