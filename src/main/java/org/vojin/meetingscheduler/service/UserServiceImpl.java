package org.vojin.meetingscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.dto.UserDto;
import org.vojin.meetingscheduler.model.Role;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.repository.UserDao;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Integer createUser(UserDto userDto) {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.addRole(new Role("USER"));
        return userDao.saveUser(user);
    }

    @Override
    public User getById(Integer id) {

        return userDao.getById(id);
    }

    @Override
    public User getByEmail(String email) {

        return userDao.getByEmail(email);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return userDao.getByUsername(username);
    }
}
