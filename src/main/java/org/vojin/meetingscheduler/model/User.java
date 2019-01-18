package org.vojin.meetingscheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @NotBlank
    @Size(max = 255, message = "Email cant't be longer than 255 characters")
    @Email(message = "Email should be of appropriate format")
    @Column
    private String email;

    @Column(unique = true)
    @NotBlank
//    @Size(min = 4, max = 15, message = "Username must be between 2 and 15 characters long")
    private String username;

    @NotBlank
//    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @JsonIgnore
    @ManyToMany(mappedBy = "attendees", cascade = CascadeType.ALL)
    private Set<Meeting> meetings = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){

        roles.add(role);
    }

    public void removeRole(Role role){

        roles.remove(role);
    }

    public Set<Role> getRoles() {

        return roles;
    }

    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Set<Meeting> getMeetings() {

        return meetings;
    }

    public void setMeetings(HashSet<Meeting> meetings) {

        this.meetings = meetings;
    }

    public void addMeeting(Meeting meeting){

        meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting){

        meetings.remove(meeting);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setUsername(String username){

        this.username = username;
    }
    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
