package interfaces;

import dto.User;

public interface IUser {
    User login(String username, String password);
}

