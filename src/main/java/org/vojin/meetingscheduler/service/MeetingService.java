package org.vojin.meetingscheduler.service;

import org.vojin.meetingscheduler.model.Meeting;


public interface MeetingService {

    int createMeeting(Meeting meeting);

    Meeting getMeeting(int meetingId);

    int createMeeting(Meeting meeting, Integer roomId);
}
