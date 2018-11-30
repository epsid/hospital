package ru.lanit.hospital.repositories;

import ru.lanit.hospital.model.Rx;

import java.util.List;

public interface RxDAO {

    Rx save(Rx prescription);

    List<Rx> findByDoctorId(int doctorId);

    List<Rx> findByPatientId(int patientId);
}
