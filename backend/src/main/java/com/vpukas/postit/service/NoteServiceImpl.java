package com.vpukas.postit.service;

import com.vpukas.postit.entity.Note;
import com.vpukas.postit.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 The {@code NoteServiceImpl} class implements the {@link NoteService} interface
 and provides functionality for handling Note objects.
 */

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    /**
     * Retrieves a list of all notes.
     * @return the list of notes.
     */
    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    /**
     * Saves a new note.
     * @param note the note to be saved.
     * @return the saved note.
     */
    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Edits an existing note.
     * @param note the updated note to be saved.
     * @param noteId the id of the note to be updated.
     * @return the updated note.
     */
    @Override
    public Note editNote(Note note, Long noteId) {
        Note searchedNote = getNoteById(noteId);
        searchedNote.setContent(note.getContent());
        return noteRepository.save(searchedNote);
    }

    /**
     * Deletes a note by its id.
     * @param noteId the id of the note to be deleted.
     */
    @Override
    public void deleteNote(Long noteId) {
        Note searchedNote = getNoteById(noteId);
        noteRepository.delete(searchedNote);
    }

    /**
     * Retrieves a note by its id.
     * @param noteId the id of the note to be retrieved.
     * @return the retrieved note.
     * @throws RuntimeException if the note with the given id does not exist.
     */
    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository
                .findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
