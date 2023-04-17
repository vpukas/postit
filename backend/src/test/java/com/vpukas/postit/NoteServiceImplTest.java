package com.vpukas.postit;

import com.vpukas.postit.entity.Note;
import com.vpukas.postit.repository.NoteRepository;
import com.vpukas.postit.service.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    private NoteServiceImpl noteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        noteService = new NoteServiceImpl(noteRepository);
    }

    @Test
    public void testGetNotes() {
        List<Note> notes = Arrays.asList(
                new Note(1L, "Note 1"),
                new Note(2L, "Note 2")
        );
        when(noteRepository.findAll()).thenReturn(notes);
        List<Note> result = noteService.getNotes();
        assertEquals(notes, result);
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void testSaveNote() {
        Note note = new Note(null, "New note");
        when(noteRepository.save(any(Note.class))).thenReturn(new Note(1L, "New note"));
        Note result = noteService.saveNote(note);
        assertEquals(1L, result.getId());
        assertEquals("New note", result.getContent());
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void testEditNote() {
        Long noteId = 1L;
        Note existingNote = new Note(noteId, "Old note");
        Note updatedNote = new Note(noteId, "New note");
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(existingNote));
        when(noteRepository.save(any(Note.class))).thenReturn(updatedNote);
        Note result = noteService.editNote(updatedNote, noteId);
        assertEquals(updatedNote, result);
        verify(noteRepository, times(1)).findById(noteId);
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void testDeleteNote() {
        Long noteId = 1L;
        Note existingNote = new Note(noteId, "Note");
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(existingNote));
        doNothing().when(noteRepository).delete(existingNote);
        noteService.deleteNote(noteId);
        verify(noteRepository, times(1)).findById(noteId);
        verify(noteRepository, times(1)).delete(existingNote);
    }

    @Test
    public void testGetNoteById() {
        Long noteId = 1L;
        Note note = new Note(noteId, "Note");
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));
        Note result = noteService.getNoteById(noteId);
        assertEquals(note, result);
        verify(noteRepository, times(1)).findById(noteId);
    }

    @Test
    public void testGetNoteByIdNotFound() {
        Long noteId = 1L;
        when(noteRepository.findById(noteId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> noteService.getNoteById(noteId));
        assertEquals("Note not found", exception.getMessage());
        verify(noteRepository, times(1)).findById(noteId);
    }

}