package org.vojin.meetingscheduler.repository;

import org.vojin.meetingscheduler.model.User;

import java.util.List;

public interface UserDao {

    List getUsers();

    void saveUser(User user);
}
