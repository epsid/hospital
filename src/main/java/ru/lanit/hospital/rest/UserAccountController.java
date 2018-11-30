package ru.lanit.hospital.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.hospital.exceptions.UserNotFoundException;
import ru.lanit.hospital.model.User;
import ru.lanit.hospital.services.DoctorService;
import ru.lanit.hospital.services.UserService;
import ru.lanit.hospital.util.ExecutionStatus;

@RestController
@RequestMapping("/account/*")
public class UserAccountController {

    final static Logger logger = LoggerFactory.getLogger(UserAccountController.class);


    UserService userService;


    DoctorService docService;

    @Autowired
    public UserAccountController(UserService userService, DoctorService docService) {
        this.docService = docService;
        this.userService = userService;
    }

    @PostMapping(value = "signup")
    public ExecutionStatus processSignup(@RequestBody User user) {

        User newUser = null;
        try {
            newUser = userService.doesUserExist(user.getEmail());
        } catch (UserNotFoundException e) {

        }
        if (newUser != null) {
            return new ExecutionStatus("USER_ALREADY_EXISTS",
                    "User with email " + user.getEmail() + " already exists." +
                            "Please try again!");
        }

        newUser = new User();
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setFirstname(user.getFirstname());
        newUser.setGender(user.getGender());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        userService.save(newUser);
        if (user.getRole() == 1) {

            docService.addDoctor(newUser);
            return new ExecutionStatus(
                    "DOCTOR_ACCOUNT_CREATED", "Doctor account successfully created! ");
        } else {
            return new ExecutionStatus(
                    "USER_ACCOUNT_CREATED", "User account successfully created! ");
        }
    }

}

