package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.note.NoteAddDTO;
import com.cnpmm.notetaking.dto.note.NoteUpdateDTO;
import com.cnpmm.notetaking.dto.notebook.NotebookUpdateDTO;
import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import com.cnpmm.notetaking.service.NoteService;
import com.cnpmm.notetaking.util.GenericResponse;
import com.cnpmm.notetaking.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController()
@RequestMapping(path = "api/v1/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Note> registerNewUser(@RequestBody NoteAddDTO noteAdd){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
        return ResponseEntity.created(uri).body(noteService.addNewNote(new Note(noteAdd.getTitle(), noteAdd.getContent()), noteAdd.getUserId()));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<GenericResponse<?>> deleteNote(@RequestParam Long noteId){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
            ServiceResponse serviceResponse = noteService.deleteNote(noteId);
            return ResponseEntity.created(uri).body(new GenericResponse<Note>(serviceResponse, null));
        }
        catch (Exception ex)
        {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<Object>(ex.getMessage(),500, null));
        }
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<Collection<Note>> getAllTag(@RequestParam Integer userId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
        return ResponseEntity.created(uri).body(noteService.findAllNoteByUser(userId));
    }

    @PutMapping(path = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<GenericResponse<Note>> updateNote(@RequestBody NoteUpdateDTO noteUpdateDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            Note updateNote = new Note(noteUpdateDTO.getTitle(),noteUpdateDTO.getContent());
            updateNote.setNoteId(noteUpdateDTO.getNoteId());
            ServiceResponse serviceResponse = noteService.updateNote(updateNote);
            return ResponseEntity.created(uri).body(new GenericResponse<Note>(serviceResponse, updateNote));
        }
        catch (Exception ex)
        {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<Note>(ex.getMessage(),500, null));
        }

    }
}
