package org.vojin.meetingscheduler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;

public class MeetingDto {

//    @NotNull(message = "Time of the meeting is required.")
//    @Past(message = "You can not schedule meeting in the past.")
//    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm")
//    private Date date;

    @DateTimeFormat
    @NotNull(message = "Time of the meeting is required.")
    @FutureOrPresent(message = "You can not schedule meeting in the past.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private Date date;

//    private Duration duration;

    private String owner;

    @NotNull(message = "Title is required.")
    private String title;

    @NotNull(message = "Room Id is required.")
    private int roomId;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public Duration getDuration() {
//        return duration;
//    }
//
//    public void setDuration(Duration duration) {
//        this.duration = duration;
//    }
}
