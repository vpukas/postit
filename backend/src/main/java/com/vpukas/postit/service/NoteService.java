package com.vpukas.postit.service;

import com.vpukas.postit.entity.Note;

import java.util.List;

/**
 Interface for the service layer of Note entity.
 */

public interface NoteService {
    /**
     * Retrieves a list of all notes.
     * @return the list of notes.
     */
    List<Note> getNotes();

    /**
     * Saves a new note.
     * @param note the note to be saved.
     * @return the saved note.
     */
    Note saveNote(Note note);

    /**
     * Edits an existing note.
     * @param note the updated note to be saved.
     * @param noteId the id of the note to be updated.
     * @return the updated note.
     */
    Note editNote(Note note, Long noteId);

    /**
     * Deletes a note by its id.
     * @param noteId the id of the note to be deleted.
     */
    void deleteNote(Long noteId);

    /**
     * Retrieves a note by its id.
     * @param noteId the id of the note to be retrieved.
     * @return the retrieved note.
     * @throws RuntimeException if the note with the given id does not exist.
     */
    Note getNoteById(Long noteId);
}
