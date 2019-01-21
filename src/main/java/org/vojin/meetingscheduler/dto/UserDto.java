package org.vojin.meetingscheduler.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    private String firstName;

    private String lastName;

    @NotBlank
    @Size(max = 255, message = "Email cant't be longer than 255 characters")
    @Email(message = "Email should be of appropriate format")
    private String email;

    @NotBlank
    @Size(min = 4, max = 15, message = "Username must be between 4 and 15 characters long")
    private String username;

    @NotBlank
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
