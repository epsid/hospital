package ru.lanit.hospital.repositories;

import ru.lanit.hospital.model.User;

import java.util.List;


public interface UserDAO {
    User save(User user);

    void update(User user);

    List<User> getByEmail(String email);

    List<User> isValidUser(String email, String password);

    List<User> getById(long id);
}
