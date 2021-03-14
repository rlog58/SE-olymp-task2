package com.se.notes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class NoteDto {
    private String title;
    private String content;
}
