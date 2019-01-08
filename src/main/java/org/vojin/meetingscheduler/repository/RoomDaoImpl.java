package org.vojin.meetingscheduler.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.Room;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Integer saveRoom(Room room) {

        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            return (Integer) session.save(room);
        }  catch (Exception e){
            //TODO
            return -1;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Room getById(Integer roomId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return session.get(Room.class, roomId);
    }

    @Override
    public List<Room> getRooms() {

        Session session = null;
        try {
            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
            return loadAllData(Room.class, session);
        } catch (Exception e) {
            //TODO
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }
}
