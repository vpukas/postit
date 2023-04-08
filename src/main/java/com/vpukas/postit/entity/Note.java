package com.vpukas.postit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(length = 200)
    private String content;
}
