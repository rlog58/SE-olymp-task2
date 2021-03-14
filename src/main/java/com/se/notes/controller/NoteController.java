package com.se.notes.controller;

import com.se.notes.database.Database;
import com.se.notes.model.Note;
import com.se.notes.model.NoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class NoteController {

    private Database database = new Database(10);

    @PostMapping("/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note add(@RequestBody NoteDto noteDto) {
        return database.add(noteDto);

    }

    @GetMapping("/notes")
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAll(@RequestParam("query") Optional<String> str ) {
        if (str.isPresent()){
            return database.query(str.get());
        } else {
            return database.getAll();
        }
    }

    @GetMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note getById(@PathVariable("id") Integer id) {
        return database.getById(id);
    }

    @PutMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody NoteDto noteDto) {
        database.update(id, noteDto);
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        database.delete(id);
    }


}
