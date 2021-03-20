package interfaces;

import dto.UserDTO;

public interface IUser {
    UserDTO login(String username, String password);
}

