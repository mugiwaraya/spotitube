package interfaces;

import dto.UserDTO;

public interface IAuthToken {
    String generateToken();

    UserDTO getUserByToken(String token);

    boolean insertToken(UserDTO userDTO, String token);
}
