package interfaces;

import domain.User;

public interface IUser {
    User login(String username, String password);
}

