package com.vpukas.postit.controller;

import com.vpukas.postit.entity.Note;
import com.vpukas.postit.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 This class defines a REST controller for handling CRUD operations for notes.
 It is annotated with @RestController, @RequestMapping, and @CrossOrigin for handling HTTP requests and setting up the mapping.
 The controller provides several HTTP endpoints for retrieving, creating, updating, and deleting notes.
 The class is also annotated with @RequiredArgsConstructor to automatically generate a constructor with final fields.
 */

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
@CrossOrigin
public class NoteController {
    private final NoteService noteService;

    /**
     * This method handles GET requests to retrieve a list of all notes.
     *
     * @return a ResponseEntity containing a list of all notes and an HTTP OK status code
     */
    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = noteService.getNotes();
        return ResponseEntity.ok(notes);
    }

    /**
     * This method handles POST requests to create a new note.
     *
     * @param note the note entity to be created
     * @return a ResponseEntity containing the created note and an HTTP CREATED status code
     */
    @PostMapping
    public ResponseEntity<Note> saveNote(@Valid @RequestBody Note note) {
        Note createdNote = noteService.saveNote(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }


    /**
     * This method handles PUT requests to update an existing note.
     *
     * @param note    the note entity with updated information
     * @param noteId  the ID of the note to be updated
     * @return a ResponseEntity containing the updated note and an HTTP CREATED status code
     */
    @PutMapping("{noteId}")
    public ResponseEntity<Note> editNote(@Valid @RequestBody Note note, @PathVariable Long noteId) {
        Note createdNote = noteService.editNote(note, noteId);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    /**
     * This method handles DELETE requests to delete a note by ID.
     *
     * @param noteId the ID of the note to be deleted
     */
    @DeleteMapping("{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
    }
    /**
     * This method handles GET requests to retrieve a note by ID.
     *
     * @param noteId the ID of the desired note
     * @return a ResponseEntity containing the desired note and an HTTP OK status code
     */
    @GetMapping("{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable Long noteId) {
        Note note = noteService.getNoteById(noteId);
        return ResponseEntity.ok(note);
    }
}
