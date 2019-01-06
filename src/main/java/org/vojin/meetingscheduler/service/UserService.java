package org.vojin.meetingscheduler.service;

import org.vojin.meetingscheduler.model.User;

import java.util.List;

public interface UserService {

    List getUsers();

    void createUser(User user);
}
