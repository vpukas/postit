package com.vpukas.postit.service;

import com.vpukas.postit.entity.Note;
import com.vpukas.postit.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    /**
     * @return list of notes
     */
    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    /**
     * @param note
     * @return
     */
    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * @param note
     * @return
     */
    @Override
    public Note editNote(Note note, Long noteId) {
        Note searchedNote = getNoteById(noteId);
        searchedNote.setContent(note.getContent());
        return noteRepository.save(searchedNote);
    }

    @Override
    public void deleteNote(Long noteId) {
        Note searchedNote = getNoteById(noteId);
        noteRepository.delete(searchedNote);
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository
                .findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }
}
