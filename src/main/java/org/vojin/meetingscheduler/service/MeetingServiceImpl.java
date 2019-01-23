package org.vojin.meetingscheduler.service;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.dto.MeetingDto;
import org.vojin.meetingscheduler.model.Meeting;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.notification.NotificationScheduler;
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
    @Autowired
    private NotificationScheduler notificationScheduler;

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
    public void inviteUser(Integer meetingId, Integer userId) {
        Meeting meeting = meetingDao.getById(meetingId);
        User user = userService.getById(userId);
        meeting.addAttendee(user);
        user.addMeeting(meeting);
        notificationScheduler.scheduleNotification(meeting, user.getEmail());
        meetingDao.updateMeeting(meeting);
    }

    @Override
    @Transactional
    public int createMeeting(MeetingDto meetingDto) {
        Meeting meeting = new Meeting();
        meeting.setOwner(meetingDto.getOwner());
        meeting.setRoom(roomService.getRoom(meetingDto.getRoomId()));
        meeting.setTitle(meetingDto.getTitle());
        meeting.setDate(meetingDto.getDate());
//        meeting.setDuration(meetingDto.getDuration());
        return meetingDao.saveMeeting(meeting);
    }
}
