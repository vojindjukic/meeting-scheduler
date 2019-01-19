package org.vojin.meetingscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.Room;
import org.vojin.meetingscheduler.repository.RoomDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomDao roomDao;

    @Override
    @Transactional
    public int createRoom(Room room) {
        return roomDao.saveRoom(room);
    }

    @Override
    @Transactional
    public Room getRoom(int roomId) {
        return roomDao.getById(roomId);
    }

    @Override
    @Transactional
    public List<Room> getRooms() {
        return roomDao.getRooms();
    }

    @Override
    @Transactional
    public void remove(int roomId) {
        roomDao.remove(getRoom(roomId));
    }

    @Override
    @Transactional
    public void edit(int roomId, Room updatedRoom) {
        Room room = getRoom(roomId);
        room.setFloor(updatedRoom.getFloor());
        room.setName(updatedRoom.getName());
        roomDao.edit(room);
    }
}
