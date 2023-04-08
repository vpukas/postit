package com.vpukas.postit.repository;

import com.vpukas.postit.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
