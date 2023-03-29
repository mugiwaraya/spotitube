package interfaces;

import dto.User;
import exceptions.TokenSavingFailedException;
import exceptions.UserNotAuthorizedException;
import exceptions.UserNotFoundByTokenException;

public interface IAuthTokenDAO {
	String generateToken();

	User getUserByToken(String token) throws UserNotFoundByTokenException;

	boolean insertToken(User user) throws TokenSavingFailedException;
}
