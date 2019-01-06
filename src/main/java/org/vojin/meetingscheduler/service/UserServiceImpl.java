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
    public List getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void createUser(User user) {
        userDao.saveUser(user);
    }
}
