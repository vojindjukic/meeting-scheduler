package org.vojin.meetingscheduler.repository;

import org.springframework.stereotype.Repository;
import org.vojin.meetingscheduler.model.Room;
import java.util.List;

@Repository
public class RoomDaoImpl extends GenericDao implements RoomDao {

    @Override
    public Integer saveRoom(Room room) {
        em.persist(room);
        return room.getRoomId();
    }

    @Override
    public Room getById(Integer roomId) {
        return getById(Room.class, roomId);
    }

//    @Override
//    public List<Room> getRooms() {
//
//        Session session = null;
//        try {
//            session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
//            return loadAllData(Room.class, session);
//        } catch (Exception e) {
//            return new ArrayList<>();
//        } finally {
//            if (session != null) session.close();
//        }
//    }

    @Override
    public List<Room> getRooms(){
        return loadAllData(Room.class);
    }

    @Override
    public void remove(Room room) {
        delete(room);
    }

    @Override
    public void edit(Room room) {
        update(room);
    }


}
