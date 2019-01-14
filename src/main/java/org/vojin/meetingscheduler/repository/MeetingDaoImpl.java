package org.vojin.meetingscheduler.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.Meeting;

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

    //Comented statements are not neccessery with @Transactional in @Service class
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
    public Meeting getById(int meetingId){
        return getById(Meeting.class, meetingId);
    }
}
