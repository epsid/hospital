package ru.lanit.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.hospital.model.Rx;
import ru.lanit.hospital.repositories.RxDAO;

import java.util.List;

@Service("rxService")
public class RxServiceImpl implements RxService {
    private RxDAO rxDAO;

    @Autowired
    public void setRxDAO(RxDAO rxDAO) {
        this.rxDAO = rxDAO;
    }

    @Override
    public void save(Rx rx) {
        this.rxDAO.save(rx);

    }

    @Override
    public List<Rx> findByDoctorId(int id) {
        return this.rxDAO.findByDoctorId(id);
    }

    @Override
    public List<Rx> findByPatientId(int id) {
        return this.rxDAO.findByPatientId(id);
    }
}
