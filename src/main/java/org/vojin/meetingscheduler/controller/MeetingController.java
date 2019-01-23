package org.vojin.meetingscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.dto.MeetingDto;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.service.MeetingService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping
    public int createMeeting(@Valid @RequestBody MeetingDto meetingDto, Principal principal){
        meetingDto.setOwner(principal.getName());
        return meetingService.createMeeting(meetingDto);
    }

    @GetMapping("/{id}")
    public Meeting getMeeting(@PathVariable (value = "id") Integer id){
        return meetingService.getMeeting(id);
    }

    @PostMapping("/{id}/user/{userId}")
    public void inviteUser(@PathVariable(value = "id") Integer meetingId, @PathVariable(value = "userId") Integer userId){
        meetingService.inviteUser(meetingId, userId);
    }
}
