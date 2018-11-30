package ru.lanit.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.hospital.exceptions.UserNotFoundException;
import ru.lanit.hospital.model.Doctor;
import ru.lanit.hospital.model.User;
import ru.lanit.hospital.repositories.DoctorDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service("doctorService")

public class DoctorServiceImpl implements DoctorService {

    private DoctorDAO doctorDAO;
    private UserService userService;

    @Autowired
    public void setDoctorDAO(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void save(Doctor doctor) {
        this.doctorDAO.save(doctor);

    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {

        return this.doctorDAO.findBySpeciality(speciality);
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorDAO.findAll();
    }

    @Override
    public Doctor findByUserEmailAddress(String email) {
        User user;
        try {
            user = this.userService.getByEmail(email);
        } catch (UserNotFoundException e) {
            return null;
        }
        return this.findByUserId((int) user.getId());
    }

    @Override
    public int findCount() {
        return this.doctorDAO.findCount();
    }

    @Override
    public Doctor findByUserId(int id) {
        return this.doctorDAO.findByUserId(id);
    }

    @Override
    public void addDoctor(User user) {
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        doctor.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        doctor.setSpecialityCode("Default");
        this.doctorDAO.save(doctor);
    }
}
