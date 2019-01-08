package org.vojin.meetingscheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.vojin.meetingscheduler.model.User;
import org.vojin.meetingscheduler.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return (List<User>) userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable (value = "id") Integer id){
        return userService.getById(id);
    }

    @PostMapping
    public Integer createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    }