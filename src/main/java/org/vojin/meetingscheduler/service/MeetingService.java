package org.vojin.meetingscheduler.service;

import org.vojin.meetingscheduler.model.Meeting;


public interface MeetingService {

    Meeting getMeeting(int meetingId);

    int createMeeting(Meeting meeting, Integer roomId);

    void inviteUser(Integer meetingId, Integer userId);

    void createMeetingAndAddUser(Meeting meeting, Integer userId);
}
