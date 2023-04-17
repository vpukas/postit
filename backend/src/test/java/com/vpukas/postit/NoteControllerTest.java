package com.vpukas.postit;

import com.vpukas.postit.controller.NoteController;
import com.vpukas.postit.entity.Note;
import com.vpukas.postit.service.NoteService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class NoteControllerTest {

    private NoteController noteController;

    @Mock
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noteController = new NoteController(noteService);
    }

    @Test
    void testGetNotes() {
        Note note1 = Note.builder().id(1L).content("Note 1").build();
        Note note2 = Note.builder().id(2L).content("Note 2").build();
        List<Note> expectedNotes = Arrays.asList(note1, note2);
        when(noteService.getNotes()).thenReturn(expectedNotes);
        ResponseEntity<List<Note>> response = noteController.getNotes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedNotes, response.getBody());
        verify(noteService, times(1)).getNotes();
    }

    @Test
    void testSaveNote() {
        Note note = Note.builder().id(1L).content("New note").build();
        when(noteService.saveNote(note)).thenReturn(note);
        ResponseEntity<Note> response = noteController.saveNote(note);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(note, response.getBody());
        verify(noteService, times(1)).saveNote(note);
    }

    @Test
    void testEditNote() {
        Note note = Note.builder().id(1L).content("Updated note").build();
        Long noteId = 1L;
        when(noteService.editNote(note, noteId)).thenReturn(note);
        ResponseEntity<Note> response = noteController.editNote(note, noteId);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(note, response.getBody());
        verify(noteService, times(1)).editNote(note, noteId);
    }

    @Test
    void testDeleteNote() {
        Long noteId = 1L;
        noteController.deleteNote(noteId);
        verify(noteService, times(1)).deleteNote(noteId);
    }

    @Test
    void testGetNote() {
        Note note = Note.builder().id(1L).content("Note 1").build();
        Long noteId = 1L;
        when(noteService.getNoteById(noteId)).thenReturn(note);
        ResponseEntity<Note> response = noteController.getNote(noteId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(note, response.getBody());
        verify(noteService, times(1)).getNoteById(noteId);
    }

    @Test
    void testSaveNoteWithValidationException() {
        Note note = Note.builder().id(1L).content("").build();
        when(noteService.saveNote(note)).thenThrow(new ConstraintViolationException(null));
        assertThrows(ConstraintViolationException.class, () -> noteController.saveNote(note));
        verify(noteService, times(1)).saveNote(note);
    }

}