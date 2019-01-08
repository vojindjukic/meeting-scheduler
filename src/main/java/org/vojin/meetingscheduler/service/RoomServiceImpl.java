package org.vojin.meetingscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.Room;
import org.vojin.meetingscheduler.repository.RoomDao;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomDao roomDao;

    @Override
    public int createRoom(Room room) {
        return roomDao.saveRoom(room);
    }

    @Override
    public Room getRoom(int roomId) {
        return roomDao.getById(roomId);
    }

    @Override
    public List<Room> getRooms() {
        return roomDao.getRooms();
    }
}
