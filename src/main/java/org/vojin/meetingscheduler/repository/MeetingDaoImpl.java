package org.vojin.meetingscheduler.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.Meeting;

import javax.persistence.EntityManagerFactory;

@Repository
public class MeetingDaoImpl implements MeetingDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public int saveMeeting(Meeting meeting) {
        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            return (Integer) session.save(meeting);
        }  catch (Exception e){
            //TODO
            return -1;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Meeting getMeeting(int meetingId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return session.get(Meeting.class, meetingId);
    }
}
