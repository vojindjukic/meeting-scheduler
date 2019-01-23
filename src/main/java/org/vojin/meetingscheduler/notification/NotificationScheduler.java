package org.vojin.meetingscheduler.notification;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.Meeting;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import java.util.Date;

@Service
public class NotificationScheduler {

    @Autowired
    private Scheduler scheduler;

    public void scheduleNotification (Meeting meeting, String email) {
        JobDetail jobDetail = buildJobDetail(meeting, email);
        Trigger trigger = buildJobTrigger(jobDetail, meeting.getDate());
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail buildJobDetail(Meeting meeting, String email){

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", email);
        jobDataMap.put("subject", "Invitation for " + meeting.getTitle() + " meeting");
        jobDataMap.put("body", "You are invited to this meeting.");

        return JobBuilder.newJob(EmailJob.class)
//                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
//                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, LocalDateTime date){
        Date notificationTime = Calendar.getInstance().getTime();
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(notificationTime.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
