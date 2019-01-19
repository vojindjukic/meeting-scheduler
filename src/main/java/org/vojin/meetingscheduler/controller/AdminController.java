package org.vojin.meetingscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.model.Room;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.service.MeetingService;
import org.vojin.meetingscheduler.service.RoomService;
import org.vojin.meetingscheduler.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private UserService userService;

    @PostMapping("/rooms")
    public int createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @PutMapping("/rooms/{roomId}")
    public void editRoom(@PathVariable int roomId, @RequestBody Room room){
        roomService.edit(roomId, room);
    }

    @DeleteMapping("/rooms/{roomId}")
    public void removeRoom(@PathVariable int roomId){
        roomService.remove(roomId);
    }

    @PutMapping("/users/{userId}")
    public void promoteUser(@PathVariable int userId, @RequestBody int roleId){
        userService.promote(userId, roleId);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.remove(userId);
    }



}
