package ru.lanit.hospital.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lanit.hospital.model.User;

import javax.transaction.Transactional;

import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        Session session = this.sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.openSession();
        User updUser = session.load(User.class, user.getId());
        Transaction tx = session.beginTransaction();
        updUser.setFirstname(user.getFirstname());
        updUser.setLastName(user.getLastName());
        updUser.setPassword(user.getPassword());
        updUser.setEmail(user.getEmail());
        updUser.setAge(user.getAge());
        updUser.setGender(user.getGender());
        session.update(user);
        tx.commit();
    }


    @Override
    public List<User> getByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<User>) session
                .getNamedQuery("getUserByEmail").setParameter("email", email).list();
    }

    @Override
    public List<User> isValidUser(String email, String password) {
        Session session = this.sessionFactory.getCurrentSession();

        return (List<User>) session.getNamedQuery("isValidUser")
                .setParameter("email", email).setParameter("password", password).list();

    }

    @Override
    public List<User> getById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<User>) session
                .getNamedQuery("getUserById").setParameter("id", id).list();
    }
}
