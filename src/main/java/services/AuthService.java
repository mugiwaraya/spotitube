package services;

import dto.UserDTO;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
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
        UserDTO userDTO = userDAO.login(requestDTO.getUsername(), requestDTO.getPassword());
        if (userDTO != null) {
            String token = tokenDAO.generateToken();
            tokenDAO.insertToken(userDTO, token);
            return Response.status(201).entity(new AuthResponseDTO(userDTO.getName(), token)).build();
        }
        return Response.status(401).build();
    }
}
