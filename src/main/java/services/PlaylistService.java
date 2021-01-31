package services;

import domain.Playlist;
import domain.Track;
import domain.User;
import dto.auth.AuthRequestDTO;
import dto.playlist.GetAllPlaylistsResponseDTO;
import interfaces.IAuthToken;
import interfaces.IPlaylist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/playlists")
public class PlaylistService {


    @Inject
    private IPlaylist playlistDAO;

    @Inject
    private IAuthToken authDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        User user = authDAO.checkToken(token);
//        ArrayList<Playlist> playlists = playlistDAO.getAllPlaylists(user.getId());
//        double length = 0;
//
//        for(Playlist playlist : playlists)
//        {
//            //TODO fix dat de duration van alle tracks bij elkaar worden opgeteld en worden geassigned aan de length variable
//        }
//        return Response.status(201).entity(new GetAllPlaylistsResponseDTO(playlists, length)).build();
        return null;
    } 
}
