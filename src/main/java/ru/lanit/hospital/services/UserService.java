package ru.lanit.hospital.services;

import ru.lanit.hospital.exceptions.UserNotFoundException;
import ru.lanit.hospital.model.User;

public interface UserService {
    User save(User user);

    void update(User user);

    User doesUserExist(String email) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;

    User isValidUser(String email, String password) throws UserNotFoundException;

    User getById(long id) throws UserNotFoundException;
}
