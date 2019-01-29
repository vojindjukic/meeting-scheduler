package org.vojin.meetingscheduler.service;

import org.vojin.meetingscheduler.dto.MeetingDto;
import org.vojin.meetingscheduler.model.Meeting;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public interface MeetingService {

    Meeting getMeeting(int meetingId);

    int createMeeting(Meeting meeting, Integer roomId);

    void inviteUser(Integer meetingId, Integer userId);

    int createMeeting(MeetingDto meetingDto);

    List<Meeting> getAllMeetings();

    List<Meeting> getMeetings(String name, LocalDateTime fromDate, LocalDateTime toDate);

    List<Meeting> getMeetings(HashMap<String,Object> params);
}
