package services;

import dto.*;
import exceptions.DeleteException;
import exceptions.InsertionException;
import interfaces.IAuthTokenDAO;
import interfaces.IPlaylistDAO;
import interfaces.ITrackDAO;
import interfaces.IUserDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("playlists")
public class PlaylistService {

    private final static Logger LOGGER = Logger.getLogger(PlaylistService.class.getName());

    private IPlaylistDAO playlistDAO;

    private IAuthTokenDAO authDAO;

    private IUserDAO userDAO;

    private ITrackDAO trackDAO;

    public PlaylistService() {
        LOGGER.setLevel(Level.WARNING);
    }

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setAuthDAO(IAuthTokenDAO authDAO) {
        this.authDAO = authDAO;
    }

    @Inject
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        try {
            User user = authDAO.getUserByToken(token);
            PlaylistsDTO response = playlistDAO.getAllPlaylists(user);
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "A problem has occured with getting all the playlists for user" + e.getMessage());
            return Response.status(400).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Produces("application/json")
    public Response getAllTracksInPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        try {
            User user = authDAO.getUserByToken(token);
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
            AddPlayListDTO playlist = new AddPlayListDTO(dto.getId(), dto.getName(), user, Collections.emptyList());
            playlistDAO.addPlaylist(playlist);
            PlaylistsDTO response = playlistDAO.getAllPlaylists(user);
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
            playlistDAO.deletePlaylist(user, playlistId);
            PlaylistsDTO response = playlistDAO.getAllPlaylists(user);
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response editPlaylist(Playlist playlist, @QueryParam("token") String token, @PathParam("id") int playlistId) {
        try {
            User user = authDAO.getUserByToken(token);
            playlistDAO.editPlaylist(playlist, playlistId);
            PlaylistsDTO response = playlistDAO.getAllPlaylists(user);
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}/tracks")
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, Track dto) {
        try {
            User user = authDAO.getUserByToken(token);
            trackDAO.addTrackToPlaylist(playlistId, dto.getId());
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
            trackDAO.removeTrackFromPlaylist(playlistId, trackId);
            Tracks response = trackDAO.getTracksInPlaylist(playlistId);
            return Response.ok().entity(response).build();
        } catch (Exception | DeleteException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }


}
