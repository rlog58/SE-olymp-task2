package com.se.notes.database;

import com.se.notes.model.Note;
import com.se.notes.model.NoteDto;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    private final Integer titleLength;
    private HashMap<Integer, Note> map = new HashMap<>();
    private Integer index = 1;

    public Database(Integer titleLength) {
        this.titleLength = titleLength;
    }

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

    public List<Note> query(String str) {
        return map.values().stream().filter(note -> (note.getTitle() != null && note.getTitle().equals(str)) || note.getContent().equals(str)).map(this::addTitle).collect(Collectors.toList());
    }

    private Note DtoToEntity(Integer id, NoteDto noteDto) {
        return new Note(id, noteDto.getTitle(), noteDto.getContent());
    }

    private Note addTitle(Note note) {
        if (note.getTitle() == null) {
            Note newNote = note;
            Integer len = note.getContent().length();
            newNote.setTitle(note.getContent().substring(0, Math.min(len, titleLength)));

            return newNote;
        } else {
            return note;
        }
    }

}
