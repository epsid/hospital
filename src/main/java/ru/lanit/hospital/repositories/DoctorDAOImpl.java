package ru.lanit.hospital.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lanit.hospital.model.Doctor;

import static java.lang.Math.toIntExact;

import javax.transaction.Transactional;
import java.util.List;


@Repository("doctorDAO")
@Transactional
public class DoctorDAOImpl implements DoctorDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Doctor save(Doctor doctor) {
        Session session = this.sessionFactory.openSession();
        session.save(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        Session session = this.sessionFactory.getCurrentSession();

        return (List<Doctor>) session.getNamedQuery("findBySpeciality")
                .setParameter("specialityCode", speciality).list();


    }

    @Override
    public List<Doctor> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Doctor>) session.getNamedQuery("findAll").list();
    }


    @Override
    public int findCount() {
        Session session = this.sessionFactory.getCurrentSession();
        int i = 0;
        List<Long> count = (List<Long>) session.getNamedQuery("findCount").list();
        if (count.size() == 0) {
            return i;
        }
        return toIntExact(count.get(0));

    }

    @Override
    public Doctor findByUserId(int userId) {

        Session session = this.sessionFactory.getCurrentSession();
        List<Doctor> count = (List<Doctor>) session.getNamedQuery("findByUserId")
                .setParameter("userId", (long) userId).list();
        return count.get(0);
    }


}
