package com.note.controllers;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.models.Note;
import com.note.repositories.NoteRepository;
import com.note.repositories.UserRepository;
import com.note.services.NoteServices;
import com.note.services.UserServices;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/notes")
@CrossOrigin("http://localhost:5173")
public class NoteController {

    @Autowired
    private UserServices userService;

    @Autowired
    private NoteServices noteService;

    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestParam String email, @RequestBody Note note) {
        return noteService.createNote(email, note);
    }
    
    

    
}
