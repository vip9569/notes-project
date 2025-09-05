package com.note.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.note.models.User;
import com.note.repositories.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<User>  createUser(User user){
        if (user != null) {
            User savedUser = userRepo.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED); //code 201
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // code 401
    }

    public ResponseEntity<User> userLogin(String email, String password) {
    Optional<User> userOpt = userRepo.findByEmail(email);

    if (userOpt.isPresent()) {
        User user = userOpt.get();

        // Check password ( In real apps, always hash passwords!)
        if (user.getPassword().equals(password)) {
            return ResponseEntity.ok(user); // 200 OK with user
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 if email not found
}


    public ResponseEntity<User> editUser(User newUser, long id){
        if(newUser != null && id !=0){
            User updatedUser = userRepo.save(newUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<User> deleteUser(String email){
        if (userRepo.existsByEmail(email)!=null) {
            userRepo.deleteByEmail(email);            
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<User>> findAllUser(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    public ResponseEntity<User> findUser(String email) {
    Optional<User> user = userRepo.findByEmail(email);

    if (user.isPresent()) {
        return ResponseEntity.ok(user.get()); // return 200 OK with user
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // return 404 Not Found
    }
}


}
