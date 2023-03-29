package services;

import dto.LoginRequestDTO;
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
	public Response login(LoginRequestDTO requestDTO) throws UserNotAuthorizedException, TokenSavingFailedException {
		return authorize(requestDTO);
	}

	private Response authorize(LoginRequestDTO requestDTO) throws UserNotAuthorizedException, TokenSavingFailedException {

		return Response.status(401).build();
	}


}
