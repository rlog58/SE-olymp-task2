package com.se.notes.database;

import com.se.notes.model.Note;
import com.se.notes.model.NoteDto;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    public static HashMap<Integer, Note> map = new HashMap<>();
    public static Integer index = 1;

    private DatabaseProperties databaseProperties;

    public Note add(NoteDto noteDto) {
        Note note = DtoToEntity(index, noteDto);
        map.put(index, note);
        index++;
        return note;
    }

    public Note getById(Integer id) {
        return addTitle(map.get(id));
    }

    public List<Note> getAll() {
        return map.values().stream().map(this::addTitle).collect(Collectors.toList());
    }

    public void update(Integer id, NoteDto noteDto) {
        map.put(id, DtoToEntity(id, noteDto));

    }

    public void delete(Integer id) {
        map.remove(id);
    }

    private Note DtoToEntity(Integer id, NoteDto noteDto) {
        return new Note(id, noteDto.getTitle(), noteDto.getContent());
    }

    private Note addTitle(Note note) {
        if (note.getTitle() == null) {
            Note newNote = note;
            Integer len = note.getContent().length();
            newNote.setTitle(note.getContent().substring(0, Math.min(len, databaseProperties.getTitleLength())));

            return newNote;
        } else {
            return note;
        }
    }

}
