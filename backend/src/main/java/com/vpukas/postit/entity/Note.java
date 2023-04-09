package com.vpukas.postit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 Represents a note entity with an id and content.
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    /**
     The unique identifier for the note entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     The content of the note entity, limited to a maximum of 200 characters.
     */
    @Size(max = 200, message = "The length of note content must be between 0 and 200 characters.")
    private String content;
}
