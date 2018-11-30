package ru.lanit.hospital.repositories;

import ru.lanit.hospital.model.Doctor;

import java.util.List;

public interface DoctorDAO {

    Doctor save(Doctor doctor);

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findAll();

    int findCount();

    Doctor findByUserId(int id);


}
