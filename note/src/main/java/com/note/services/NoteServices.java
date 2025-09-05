package com.note.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.note.models.Note;
import com.note.models.User;
import com.note.repositories.NoteRepository;
import com.note.repositories.UserRepository;

@Service
public class NoteServices {

    @Autowired
    private NoteRepository noteRepo;

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<Note> createNote(String email, Note note) {
        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            note.setUser(user);
            Note savedNote = noteRepo.save(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Note> updateNote(long userId, Note newNote) {
        if (newNote != null) {
            Note updatedNote = noteRepo.save(newNote);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> deleteNote(long id) {
        if (noteRepo.existsById(id)) {
            noteRepo.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    public ResponseEntity<List<Note>> findAllNotes(){
        return ResponseEntity.ok(noteRepo.findAll());
    }

    public ResponseEntity<Optional<Note>> findNoteByUserId(long id){
        if(noteRepo.existsById(id)){
            return ResponseEntity.ok(noteRepo.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

}
