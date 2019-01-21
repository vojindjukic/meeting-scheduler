package org.vojin.meetingscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.service.MeetingService;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping ("/user/{id}")
    public void createMeeting(@RequestBody Meeting meeting, @PathVariable(value = "id") Integer userId){
        meetingService.createMeetingAndAddUser(meeting, userId);
    }

    @GetMapping("/{id}")
    public Meeting getUser(@PathVariable (value = "id") Integer id){
        return meetingService.getMeeting(id);
    }

    @PostMapping("/{id}/user/{userId}")
    public void inviteUser(@PathVariable(value = "id") Integer meetingId, @PathVariable(value = "userId") Integer userId){
        meetingService.inviteUser(meetingId, userId);
    }
}
