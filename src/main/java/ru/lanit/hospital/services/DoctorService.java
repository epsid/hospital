package ru.lanit.hospital.services;

import ru.lanit.hospital.model.Doctor;
import ru.lanit.hospital.model.User;

import java.util.List;

public interface DoctorService {
    void save(Doctor doctor);

    List findBySpeciality(String speciality);

    List<Doctor> findAll();

    Doctor findByUserEmailAddress(String email);

    int findCount();

    Doctor findByUserId(int id);

    void addDoctor(User user);


}
