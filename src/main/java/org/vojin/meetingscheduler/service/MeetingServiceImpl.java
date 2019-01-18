package org.vojin.meetingscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.repository.MeetingDao;

import javax.transaction.Transactional;


@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingDao meetingDao;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Meeting getMeeting(int meetingId) {
        return meetingDao.getById(meetingId);
    }

    @Override
    @Transactional
    public int createMeeting(Meeting meeting, Integer roomId) {
        meeting.setRoom(roomService.getRoom(roomId));
        return meetingDao.saveMeeting(meeting);
    }

    @Override
    @Transactional
    public void createMeetingAndAddUser(Meeting meeting, Integer userId) {
        User user = userService.getById(userId);
        meeting.addAttendee(user);
        meetingDao.saveMeeting(meeting);
    }

    @Override
    @Transactional
    public void inviteUser(Integer meetingId, Integer userId) {
        Meeting meeting = meetingDao.getById(meetingId);
        User user = userService.getById(userId);
        meeting.addAttendee(user);
        user.addMeeting(meeting);
        meetingDao.updateMeeting(meeting);
    }
}
