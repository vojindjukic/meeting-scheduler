package org.vojin.meetingscheduler.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.Role;
import org.vojin.meetingscheduler.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDaoImpl extends GenericDao implements UserDao {

    @Override
    public User getById(Integer id) {
        return getById(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return loadAllData(User.class);
    }

    @Override
    public Integer saveUser(User user) {
        em.persist(user);
        return user.getId();
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

    public Role getRoleById(int id) {
        return getById(Role.class, id);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void remove(User user) {
        delete(user);
    }
}
