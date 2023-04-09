package com.vpukas.postit.repository;

import com.vpukas.postit.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 The NoteRepository interface provides CRUD functionality for Note entity.
 It extends JpaRepository which provides the implementation of common database operations.
 */

public interface NoteRepository extends JpaRepository<Note, Long> {

}
