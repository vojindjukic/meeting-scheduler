package org.vojin.meetingscheduler.service;

import org.vojin.meetingscheduler.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    Integer createUser(User user);

    User getById(Integer id);
}
