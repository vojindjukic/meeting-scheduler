package org.vojin.meetingscheduler.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.vojin.meetingscheduler.dto.UserDto;
import org.vojin.meetingscheduler.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();

    Integer createUser(UserDto userDto);

    User getById(Integer id);

    User getByEmail(String email);

    User loadUserByUsername(String username);

    void promote(int id, int role);

    void remove(int userId);
}
