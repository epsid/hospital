package ru.lanit.hospital.services;

import ru.lanit.hospital.model.Rx;

import java.util.List;

public interface RxService {
    void save(Rx rx);

    List<Rx> findByDoctorId(int id);

    List<Rx> findByPatientId(int id);

}
