package org.vojin.meetingscheduler.model;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="meetings")
public class Meeting {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingId;

    @Column
    private String owner;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

//    @Column
//    private Duration duration;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="attendees", joinColumns = @JoinColumn(name = "meetingId"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    private Set<User> attendees = new HashSet<>();


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

    public void addAttendee(User user){
        attendees.add(user);
        user.addMeeting(this);
    }

    public void removeAttendee(User user){
        attendees.remove(user); }

    public Set<User> getAttendees() {
        return attendees; }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees; }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
