package com.vpukas.postit.controller;

import com.vpukas.postit.entity.Note;
import com.vpukas.postit.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    /**
     * @return list of notes
     */
    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = noteService.getNotes();
        return ResponseEntity.ok(notes);
    }

    /**
     * @param note
     * @return
     */
    @PostMapping
    public ResponseEntity<Note> saveNote(@Valid @RequestBody Note note) {
        Note createdNote = noteService.saveNote(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    /**
     * @param note
     * @return
     */
    @PutMapping("{noteId}")
    public ResponseEntity<Note> editNote(@Valid @RequestBody Note note, @PathVariable Long noteId) {
        Note createdNote = noteService.editNote(note, noteId);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @DeleteMapping("{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
    }
}
