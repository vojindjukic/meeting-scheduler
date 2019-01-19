package org.vojin.meetingscheduler.repository;


import org.vojin.meetingscheduler.model.Room;

import java.util.List;

public interface RoomDao {

    Integer saveRoom(Room room);

    Room getById(Integer roomId);

    List<Room> getRooms();

    void remove(Room room);

    void edit(Room room);
}
