package services;

import domain.User;
import dto.auth.AuthRequestDTO;
import dto.auth.AuthResponseDTO;
import interfaces.IAuthToken;
import interfaces.IUser;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationPath("")
@Path("/login")
public class AuthService {

    @Inject
    private IAuthToken tokenDAO;

    @Inject
    private IUser userDAO;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(AuthRequestDTO requestDTO) {
        return authorize(requestDTO);
    }

    private Response authorize(AuthRequestDTO requestDTO) {
        User user = userDAO.login(requestDTO.getUsername(), requestDTO.getPassword());
        if (user != null) {
            String token = tokenDAO.generateToken();
            tokenDAO.insertToken(user, token);
            return Response.status(201).entity(new AuthResponseDTO(user.getName(), token)).build();
        }
        return Response.status(401).build();
    }
}
