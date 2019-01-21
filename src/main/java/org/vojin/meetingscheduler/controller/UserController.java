package org.vojin.meetingscheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.dto.UserDto;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.service.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping("/sign-up")
    public Integer registerUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping(params = "email")
    public User getUserByEmail(@RequestParam String email){
        return userService.getByEmail(email);
    }

    }