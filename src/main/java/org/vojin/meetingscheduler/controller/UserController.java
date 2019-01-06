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

//    @GetMapping
//    public ResponseEntity<List<User>> userDetails() {
//
//        List<User> users = userService.getUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
    @GetMapping
    public List<User> userDetails() {

        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping
    public void createUser(@RequestBody User user){

        userService.createUser(user);
    }

    }