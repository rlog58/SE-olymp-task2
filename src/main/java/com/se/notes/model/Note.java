package com.se.notes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Note {
    private Integer id;
    private String title;
    private String content;

    public Note(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
