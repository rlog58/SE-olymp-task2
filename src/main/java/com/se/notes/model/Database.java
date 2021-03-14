package com.se.notes.model;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    public static HashMap<Integer, Note> map;
    public static Integer index = 1;

    public Note add(NoteDto noteDto) {
        Note note = DtoToEntity(index, noteDto);
        map.put(index, note);
        index++;
        return note;
    }

    public Note getById(Integer id) {
        return map.get(id);
    }

    public List<Note> getAll() {
        return map.values().stream().collect(Collectors.toList());
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

}
