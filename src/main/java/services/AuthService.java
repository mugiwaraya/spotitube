package services;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.User;
import exceptions.TokenSavingFailedException;
import exceptions.UserNotAuthorizedException;
import interfaces.IAuthTokenDAO;
import interfaces.IUserDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class AuthService {


	private IAuthTokenDAO authTokenDAO;
	private IUserDAO userDAO;

	@Inject
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Inject
	public void setAuthTokenDAO(IAuthTokenDAO authTokenDAO) {
		this.authTokenDAO = authTokenDAO;
	}


	public AuthService() {

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(AuthRequestDTO requestDTO) throws UserNotAuthorizedException, TokenSavingFailedException {
		return authorize(requestDTO);
	}

	private Response authorize(AuthRequestDTO requestDTO) throws UserNotAuthorizedException, TokenSavingFailedException {
		User user = userDAO.login(requestDTO.getUsername(), requestDTO.getPassword());
		if (user != null) {
			User updatedUser = authTokenDAO.insertToken(user);
			return Response.status(201).entity(new AuthResponseDTO(user.getName(), updatedUser.getToken())).build();
		}
		return Response.status(401).build();
	}


}
