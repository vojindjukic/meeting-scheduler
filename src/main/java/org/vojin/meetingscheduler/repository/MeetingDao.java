package org.vojin.meetingscheduler.repository;

import org.vojin.meetingscheduler.model.Meeting;

import java.util.List;

public interface MeetingDao {

    int saveMeeting(Meeting meeting);

    void updateMeeting(Meeting meeting);

    Meeting getById(int id);

    void addUser(Integer meetingId, Integer userId);
}
