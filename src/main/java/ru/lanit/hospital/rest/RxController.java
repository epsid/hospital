package ru.lanit.hospital.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.lanit.hospital.exceptions.UserNotFoundException;
import ru.lanit.hospital.model.Doctor;
import ru.lanit.hospital.model.Rx;
import ru.lanit.hospital.model.User;
import ru.lanit.hospital.services.DoctorService;
import ru.lanit.hospital.services.RxService;
import ru.lanit.hospital.services.UserService;
import ru.lanit.hospital.util.RxDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RxController {

    final static Logger logger = LoggerFactory.getLogger(RxController.class);

    @Autowired
    private DoctorService docService;
    @Autowired
    private RxService rxService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/rx", produces = "application/json")
    public List<RxDTO> getRX() {

        User user = getUser();

        List<Rx> rxList;
        if (user.getRole() == 1) {

            rxList = rxService.findByDoctorId((int) user.getId());
            System.out.println(rxList.size());
        } else {
            rxList = rxService.findByPatientId((int) user.getId());
        }
        List<RxDTO> rxDTOList = new ArrayList<>();
        for (Rx rx : rxList) {
            RxDTO rxDTO = new RxDTO();
            rxDTO.setMedicine(rx.getMedicine());
            rxDTO.setSymptoms(rx.getSymptoms());
            rxDTO.setPatientName(rx.getUser().getFirstname());
            rxDTOList.add(rxDTO);
        }
        return rxDTOList;
    }

    @PostMapping(value = "/rx/new", produces = "application/json")
    public Rx createRx(@RequestBody RxDTO rxDTO) {
        Rx rx = new Rx();
        rx.setSymptoms(rxDTO.getSymptoms());
        rx.setMedicine(rxDTO.getMedicine());
        User user = null;
        try {
            user = userService.getByEmail(rxDTO.getPatientId());
        } catch (UserNotFoundException e) {

        }
        rx.setUser(user);

        Doctor doctor = docService.findByUserEmailAddress(getUserEmailAdress());
        rx.setDoctor(doctor);
        rx.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        rx.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));

        rxService.save(rx);
        return rx;
    }

    private User getUser() {
        String email = getUserEmailAdress();
        User user = null;
        try {
            user = userService.getByEmail(email);
        } catch (UserNotFoundException e) {
        }
        return user;
    }

    private String getUserEmailAdress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
