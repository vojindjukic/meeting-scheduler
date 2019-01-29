package org.vojin.meetingscheduler.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.Meeting;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MeetingDaoImpl extends GenericDao implements MeetingDao {

    @Autowired
    private UserDao userDao;

    @Override
    public int saveMeeting(Meeting meeting) {
        em.persist(meeting);
        return meeting.getMeetingId();
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        em.merge(meeting);
    }

    //Commented statements are not neccessery with @Transactional in @Service class
    @Override
    public void addUser(Integer meetingId, Integer userId){
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
        Meeting meeting = getById(meetingId);
        meeting.addAttendee(userDao.getById(userId));
        em.merge(meeting);
//        transaction.commit();
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return loadAllData(Meeting.class);
    }

    @Override
    public List<Meeting> getMeetings(Specification spec) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Meeting> query = builder.createQuery(Meeting.class);
        Root<Meeting> root = query.from(Meeting.class);
        query.where(spec.toPredicate(root, query, builder));
        TypedQuery<Meeting> typedQuery = em.createQuery(query);
        try{
            return typedQuery.getResultList();
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Meeting getById(int meetingId){
        return getById(Meeting.class, meetingId);
    }

}
