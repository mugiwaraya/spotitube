package services;

import dto.*;

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
		UserDTO userDTO = authDAO.getUserByToken(token);
		PlaylistsDTO response = playlistDAO.getAllPlaylists(userDTO.getId());
		return Response.ok().entity(response).build();
	}

	@GET
	@Path("/{id}/tracks")
	@Produces("application/json")
	public Response getAllTracksInPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
		try {
			UserDTO userDTO = authDAO.getUserByToken(token);
			if (userDTO == null) {
				return Response.status(403).build();
			}
			TracksDTO response = trackDAO.getTracksInPlaylist(playlistId);
			if (response.getTracks() != null) {
				return Response.ok().entity(response).build();
			}
			return Response.ok().entity(new ArrayList<TrackDTO>()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).build();
		}
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPlaylist(PlaylistDTO dto, @QueryParam("token") String token) {
		try {
			UserDTO userDTO = authDAO.getUserByToken(token);
			if (userDTO == null) {
				return Response.status(403).build();
			}
			AddPlayListDTO playlist = new AddPlayListDTO(dto.getId(), dto.getName(), userDTO.getId(), Collections.emptyList());
			playlistDAO.addPlaylist(playlist);
			PlaylistsDTO response = playlistDAO.getAllPlaylists(userDTO.getId());
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
			UserDTO userDTO = authDAO.getUserByToken(token);
			if (userDTO == null) {
				return Response.status(403).build();
			}
			playlistDAO.deletePlaylist(playlistId);
			PlaylistsDTO response = playlistDAO.getAllPlaylists(userDTO.getId());
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
	public Response editPlaylist(PlaylistDTO playlist, @QueryParam("token") String token, @PathParam("id") int playlistId) {
		try {
			UserDTO userDTO = authDAO.getUserByToken(token);
			if (userDTO == null) {
				return Response.status(403).build();
			}
			playlistDAO.editPlaylist(playlist, playlistId);
			PlaylistsDTO response = playlistDAO.getAllPlaylists(userDTO.getId());
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
	public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO dto) {
		try {
			UserDTO userDTO = authDAO.getUserByToken(token);
			if (userDTO == null) {
				return Response.status(403).build();
			}
			trackDAO.addTrackToPlaylist(playlistId, dto);
			TracksDTO response = trackDAO.getTracksInPlaylist(playlistId);
			return Response.ok().entity(response).build();
		} catch (Exception | InsertionException e) {
			return Response.status(400).build();
		}
	}

}
