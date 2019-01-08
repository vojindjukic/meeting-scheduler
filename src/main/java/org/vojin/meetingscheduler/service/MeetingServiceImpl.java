package org.vojin.meetingscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.repository.MeetingDao;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingDao meetingDao;
    @Autowired
    private RoomService roomService;

    @Override
    public int createMeeting(Meeting meeting) {
        return meetingDao.saveMeeting(meeting);
    }

    @Override
    public Meeting getMeeting(int meetingId) {
        return meetingDao.getMeeting(meetingId);
    }

    @Override
    public int createMeeting(Meeting meeting, Integer roomId) {
        meeting.setRoom(roomService.getRoom(roomId));
        return meetingDao.saveMeeting(meeting);
    }
}
