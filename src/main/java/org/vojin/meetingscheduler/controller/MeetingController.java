package org.vojin.meetingscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping
    public Integer createMeeting(@RequestBody Meeting meeting){
        return meetingService.createMeeting(meeting);
    }

    @GetMapping("/{id}")
    public Meeting getUser(@PathVariable (value = "id") Integer id){
        return meetingService.getMeeting(id);
    }
}
