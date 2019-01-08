package org.vojin.meetingscheduler.service;

import org.vojin.meetingscheduler.model.Room;

import java.util.List;

public interface RoomService {

    int createRoom(Room room);

    Room getRoom(int roomId);

    List<Room> getRooms();
}
