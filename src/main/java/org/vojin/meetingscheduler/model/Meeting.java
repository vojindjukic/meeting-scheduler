package org.vojin.meetingscheduler.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name="meetings")
public class Meeting {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingId;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;


//    @Column
//    private Date date;
//    @Column
//    private Duration duration;

//    @ManyToMany
//    private HashSet<User> attendees;


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
