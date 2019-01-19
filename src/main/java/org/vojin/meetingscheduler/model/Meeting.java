package org.vojin.meetingscheduler.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return meetingId == meeting.meetingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId);
    }
}
