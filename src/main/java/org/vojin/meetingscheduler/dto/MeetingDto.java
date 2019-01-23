package org.vojin.meetingscheduler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class MeetingDto {


    @NotNull(message = "Time of the meeting is required.")
    @FutureOrPresent(message = "You can not schedule meeting in the past.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
