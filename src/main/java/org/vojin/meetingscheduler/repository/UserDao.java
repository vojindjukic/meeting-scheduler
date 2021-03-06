package org.vojin.meetingscheduler.repository;

import org.vojin.meetingscheduler.model.Role;
import org.vojin.meetingscheduler.model.User;

import java.util.List;

public interface UserDao {

    User getById(Integer id);

    List<User> getUsers();

    Integer saveUser(User user);

    User getByEmail(String email);

    User getByUsername(String username);

    Role getRoleById(int id);

    void updateUser(User user);

    void remove(User user);
}
