package org.vojin.meetingscheduler.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl extends GenericDao implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public User getById(Integer id) {
        entityManagerFactory.createEntityManager();
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return session.get(User.class, id);
    }

    //TODO: Handle Sessions with @Transactional in UserService class

    @Override
    public List<User> getUsers() {
        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            return loadAllData(User.class);
        } catch (Exception e) {
            //TODO
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Integer saveUser(User user) {
        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            return (Integer) session.save(user);
        }  catch (Exception e){
            //TODO
            return -1;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public User getByEmail(String email) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> from = criteria.from(User.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get("email"), email));
        TypedQuery<User> typed = em.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }

    @Override
    public User getByUsername(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> from = criteria.from(User.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get("username"), username));
        TypedQuery<User> typed = em.createQuery(criteria);
        try {
            return typed.getSingleResult();
        } catch (final NoResultException nre) {
            return null;
        }
    }
}
