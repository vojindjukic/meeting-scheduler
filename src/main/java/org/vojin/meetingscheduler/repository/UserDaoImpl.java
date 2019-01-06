package org.vojin.meetingscheduler.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<User> getUsers() {
        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(User.class);
            Root contactRoot = criteria.from(User.class);
            criteria.select(contactRoot);
            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            //TODO
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }
    @Override
    public void saveUser(User user) {
        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            session.save(user);
        }  catch (Exception e){
            //TODO
        } finally {
            if (session != null) session.close();
        }
    }

}
