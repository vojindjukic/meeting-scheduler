package org.vojin.meetingscheduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vojin.meetingscheduler.dto.UserDto;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.repository.UserDao;

import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public Integer createUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.addRole(userDao.getRoleById(1)); //basic 'User' role
        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    //required by UserDetailsService interface
    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getByUsername(username);
    }

    @Override
    @Transactional
    public void promote(int userId, int roleId) {
        User user = getById(userId);
        user.addRole(userDao.getRoleById(roleId));
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void remove(int userId) {
        userDao.remove(getById(userId));
    }
}
