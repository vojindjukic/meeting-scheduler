package org.vojin.meetingscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.model.Room;
import org.vojin.meetingscheduler.service.MeetingService;
import org.vojin.meetingscheduler.service.RoomService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private MeetingService meetingService;

    @GetMapping
    public List<Room> getRooms(){
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable (value = "id") Integer id){
        return roomService.getRoom(id); }

    @PostMapping("/{id}/meeting")
    public int createMeeting(@RequestBody Meeting meeting, @PathVariable (value = "id") Integer roomId, Principal principal){
        meeting.setOwner(principal.getName());
        return meetingService.createMeeting(meeting, roomId);
    }
}
