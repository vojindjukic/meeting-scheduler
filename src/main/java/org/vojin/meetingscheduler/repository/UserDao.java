package org.vojin.meetingscheduler.repository;

import org.vojin.meetingscheduler.model.User;

import java.util.List;

public interface UserDao {

    User getById(Integer id);

    List<User> getUsers();

    Integer saveUser(User user);
}
