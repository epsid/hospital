package ru.lanit.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.hospital.exceptions.UserNotFoundException;
import ru.lanit.hospital.model.User;
import ru.lanit.hospital.repositories.UserDAO;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User save(User user) {
        return this.userDAO.save(user);
    }

    @Override
    public void update(User user) {
        this.userDAO.update(user);

    }

    @Override
    public User doesUserExist(String email) throws UserNotFoundException {
        List<User> user = this.userDAO.getByEmail(email);
        if (user.size() == 0) {
            throw new UserNotFoundException("Can not find User with email " + email + "." + " Please try again!");
        }
        return user.get(0);
    }

    @Override
    public User getByEmail(String email) throws UserNotFoundException {

        return this.doesUserExist(email);
    }


    @Override
    public User isValidUser(String email, String password) throws UserNotFoundException {
        List<User> user = this.userDAO.isValidUser(email, password);
        if (user == null || user.size() == 0) {
            throw new UserNotFoundException("Email or password is invalid. Please, try again! ");
        }
        return user.get(0);
    }

    @Override
    public User getById(long id) throws UserNotFoundException {
        List<User> user = this.userDAO.getById(id);
        if (user.size() == 0) {
            throw new UserNotFoundException("Can not find User with id " + id + "." + " Please try again!");
        }
        return user.get(0);
    }


}
