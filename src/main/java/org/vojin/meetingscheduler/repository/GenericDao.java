package org.vojin.meetingscheduler.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao {

    @PersistenceContext
    protected EntityManager em;

    protected  <T> List<T> loadAllData(Class<T> type) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return em.createQuery(criteria).getResultList();
    }

    protected  <T>  T getById(Class<T> type, int id){
        return em.find(type, id);
    }

    protected <T> void delete(T t){
        em.remove(t);
    }

    protected <T> T update(T t) {
        return em.merge(t);
    }

    protected <T> T create(T t) {
        em.persist(t);
        return t;
    }
}
