package org.vojin.meetingscheduler.repository;

import org.vojin.meetingscheduler.model.Meeting;

import java.util.List;

public interface MeetingDao extends GenericDao {

    int saveMeeting(Meeting meeting);

    Meeting getMeeting(int meetingId);
}
