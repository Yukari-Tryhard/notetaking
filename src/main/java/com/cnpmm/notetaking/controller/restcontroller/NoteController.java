package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dao.NoteAddDAO;
import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController()
@RequestMapping(path = "api/v1/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Note> registerNewUser(@RequestBody NoteAddDAO noteAdd){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
        return ResponseEntity.created(uri).body(noteService.addNewNote(new Note(noteAdd.getTitle(), noteAdd.getContent()), noteAdd.getUserId()));
    }

}
