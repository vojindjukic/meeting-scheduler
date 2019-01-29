package org.vojin.meetingscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.model.Room;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.service.MeetingService;
import org.vojin.meetingscheduler.service.RoomService;
import org.vojin.meetingscheduler.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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

//    @GetMapping("/meetings")
//    public List<Meeting> getAllMeetings(){
//        return meetingService.getAllMeetings();
//    }

//    @GetMapping("/meetings")
//    public List<Meeting> getMeetings(@RequestParam HashMap<String,Object> params){
//        return meetingService.getMeetings(params);
//    }

        @GetMapping("/meetings")
    public List<Meeting> getMeetings(@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime fromDate,
                                     @RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime toDate,
                                     @RequestParam(name="owner", required = false) String owner){

        HashMap<String, Object> params = new HashMap<>();
        System.out.println("fromDate: " + fromDate + "toDate: " + toDate);
        if (fromDate != null) params.put("fromDate", fromDate);
        if (toDate != null)params.put("toDate", toDate);
        if (owner != null)params.put("owner", owner);
        return meetingService.getMeetings(params);
    }


}
