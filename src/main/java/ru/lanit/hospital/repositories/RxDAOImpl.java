package ru.lanit.hospital.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lanit.hospital.model.Rx;

import javax.transaction.Transactional;
import java.util.List;

@Repository("rxDAO")
@Transactional
public class RxDAOImpl implements RxDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Rx save(Rx prescription) {
        Session session = this.sessionFactory.openSession();
        session.save(prescription);
        return prescription;

    }

    @Override
    public List<Rx> findByDoctorId(int doctorId) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Rx> query = session.getNamedQuery("findByDoctorId")
                .setParameter("doctorId", ((long) doctorId));
        List<Rx> list = query.list();

        return list;
    }

    @Override
    public List<Rx> findByPatientId(int patientId) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Rx> query = session.getNamedQuery("findByPatientId")
                .setParameter("userId", ((long) patientId));
        List<Rx> list = query.list();
        return list;
    }
}
