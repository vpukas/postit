package com.vpukas.postit.service;

import com.vpukas.postit.entity.Note;

import java.util.List;

public interface NoteService {
    /**
     * @return list of notes
     */
    List<Note> getNotes();

    /**
     * @param note
     * @return
     */
    Note saveNote(Note note);

    /**
     * @param note
     * @return
     */
    Note editNote(Note note, Long noteId);

    void deleteNote(Long noteId);

    Note getNoteById(Long noteId);
}
