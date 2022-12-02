package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.note.NoteAddDTO;
import com.cnpmm.notetaking.dto.note.NoteUpdateDTO;
import com.cnpmm.notetaking.dto.notebook.NotebookUpdateDTO;
import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.service.NoteService;
import com.cnpmm.notetaking.service.TagService;
import com.cnpmm.notetaking.service.UserService;
import com.cnpmm.notetaking.util.GenericResponse;
import com.cnpmm.notetaking.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    public NoteController(NoteService noteService, UserService userService, TagService tagService){
        this.noteService = noteService;
        this.userService = userService;
        this.tagService = tagService;
    }
    @PostMapping(path = "/add")
    public ResponseEntity<GenericResponse<Note>> registerNewNote(@RequestBody NoteAddDTO noteAdd){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            ArrayList<Tag> tags = new ArrayList<Tag>();
            noteAdd.getTags().forEach(tagName -> {
                Tag tag = new Tag(tagName);
                tag.setUser(userService.findById(Integer.parseInt(noteAdd.getUserId())));
                tag = tagService.saveTag(tag);
                tags.add(tag);
            });
            Note note = new Note(noteAdd.getTitle(), noteAdd.getContent(), tags);
            ServiceResponse noteServiceResponse = noteService.addNewNote(note,noteAdd.getUserId());
            return ResponseEntity.created(uri).body(new GenericResponse<Note>(noteServiceResponse, note));
        }
        catch (Exception ex)
        {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<Note>(ex.getMessage(),500, null));
        }
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
    public  ResponseEntity<GenericResponse<?>> getAllNote(@RequestParam Integer userId){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>("get all succesfully", 200,noteService.findAllNoteByUser(userId)));
        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @PutMapping(path = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<GenericResponse<Note>> updateNote(@RequestBody NoteUpdateDTO noteUpdateDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            ArrayList<Tag> tags = new ArrayList<>();
            noteService.clearTag(noteUpdateDTO.getNoteId());
            Note note = noteService.findById(noteUpdateDTO.getNoteId());
            noteUpdateDTO.getTags().forEach(tagName -> {
                Tag tag = new Tag(tagName);
                tag.setNote(note);
                tag.setUser(userService.findById(noteUpdateDTO.getUserId()));
                tag = tagService.saveTag(tag);
                tags.add(tag);
            });
            Note updateNote = new Note(noteUpdateDTO.getTitle(),noteUpdateDTO.getContent(), tags);
            updateNote.setNoteId(noteUpdateDTO.getNoteId());
            updateNote.setUser(userService.findById(noteUpdateDTO.getUserId()));
            updateNote.setTags(tags);
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
