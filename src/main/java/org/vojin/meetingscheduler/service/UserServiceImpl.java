package org.vojin.meetingscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.repository.UserDao;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Integer createUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }
}
