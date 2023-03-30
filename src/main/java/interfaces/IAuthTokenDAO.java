package interfaces;

import dto.User;
import exceptions.TokenSavingFailedException;
import exceptions.UserNotFoundByTokenException;

public interface IAuthTokenDAO {
	String generateToken();

	User getUserByToken(String token) throws UserNotFoundByTokenException;

	User insertToken(User user) throws TokenSavingFailedException;
}
