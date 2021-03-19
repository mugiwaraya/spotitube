package interfaces;

import domain.User;

import java.util.UUID;

public interface IAuthToken {
    String generateToken();

    User getUserByToken(String token);

    boolean insertToken(User user, String token);
}
