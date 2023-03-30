package interfaces;

import dto.User;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;

public interface IUserDAO {
    User login(String username, String password) throws UserNotAuthorizedException;

    boolean authorizedByToken(String token) throws UserNotFoundByTokenException;
}

