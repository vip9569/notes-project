package com.note.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.models.User;
import com.note.services.UserServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:5173")
public class UserController {

    @Autowired
    private UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return userService.userLogin(email, password);
    }

    @GetMapping("/user")   
    public ResponseEntity<List<User>> getAllUser(){
        return userService.findAllUser();
    }
    

}
