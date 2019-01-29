package org.vojin.meetingscheduler.notification;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.Meeting;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import java.util.Date;

@Service
public class NotificationScheduler {

    @Autowired
    private Scheduler scheduler;

    private static final String INVITATION_SUBJECT = "Invitation for %s meeting";
    private static final String INVITATION_BODY = "%s is inviting you to %s meeting. Please confirm your attendance.";
    private static final String REMINDER_SUBJECT = "Reminder for %s meeting";
    private static final String REMINDER_BODY = "%s is starting in %d minutes.";
    private static final int REMINDER_TIME_BEFORE_MEETING = 15; //in minutes

    public void scheduleInvitation(Meeting meeting, String email){
       JobDataMap invitationJobDataMap = buildInvitationJobDataMap(email, meeting);
       JobDetail invitationJob = buildEmailJob(invitationJobDataMap);
       Trigger trigger = buildEmailJobTrigger(invitationJob, LocalDateTime.now(), 0);
       try {
            scheduler.scheduleJob(invitationJob, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void scheduleReminder(Meeting meeting, String email){
        JobDataMap reminderJobDataMap = buildReminderJobDataMap(email, meeting);
        JobDetail reminderJob = buildEmailJob(reminderJobDataMap);
        Trigger trigger = buildEmailJobTrigger(reminderJob, meeting.getDate(), REMINDER_TIME_BEFORE_MEETING);
        try {
            scheduler.scheduleJob(reminderJob, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail buildEmailJob(JobDataMap jobDataMap){
        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Reminder Email Job")
                .usingJobData(jobDataMap)
                .build();
    }

    private JobDataMap buildInvitationJobDataMap(String email, Meeting meeting){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", email);
        jobDataMap.put("subject", String.format(INVITATION_SUBJECT, meeting.getTitle()));
        jobDataMap.put("body", String.format(INVITATION_BODY, meeting.getOwner(), meeting.getTitle()));
        return jobDataMap;
    }

    private JobDataMap buildReminderJobDataMap(String email, Meeting meeting){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", email);
        jobDataMap.put("subject", String.format(REMINDER_SUBJECT, meeting.getTitle()));
        jobDataMap.put("body", String.format(REMINDER_BODY, meeting.getTitle(), REMINDER_TIME_BEFORE_MEETING));
        return jobDataMap;
    }

    private Trigger buildEmailJobTrigger(JobDetail jobDetail, LocalDateTime date, int minutesBefore){
        LocalDateTime notificationTime = date.plusMinutes(-minutesBefore);
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Email Trigger")
                .startAt(convertToDate(notificationTime))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

    private Date convertToDate(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
