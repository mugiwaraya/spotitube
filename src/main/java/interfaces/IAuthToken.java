package interfaces;

import dto.User;

public interface IAuthToken {
    String generateToken();

    User getUserByToken(String token);

    boolean insertToken(User user, String token);
}
