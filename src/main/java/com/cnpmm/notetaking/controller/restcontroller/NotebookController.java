package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.note.NoteAddDTO;
import com.cnpmm.notetaking.dto.notebook.NotebookAddDTO;
import com.cnpmm.notetaking.dto.notebook.NotebookAddNoteDTO;
import com.cnpmm.notetaking.dto.notebook.NotebookUpdateDTO;
import com.cnpmm.notetaking.dto.tag.TagUpdateDTO;
import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController()
@RequestMapping(path = "api/v1/notebook")
public class NotebookController {
    @Autowired
    private NotebookService notebookService;

    public NotebookController(NotebookService notebookService){
        this.notebookService = notebookService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Notebook> registerNewNotebook(@RequestBody NotebookAddDTO notebookAddDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
        return ResponseEntity.created(uri).body(notebookService.addNewNoteBook(new Notebook(notebookAddDTO.getNotebookName()), notebookAddDTO.getUserId()));
    }

    @PostMapping(path = "/add-note")
    public ResponseEntity<Notebook> registerNewNoteToNotebook(@RequestBody NotebookAddNoteDTO notebookAddNoteDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add-note").toUriString());
        return ResponseEntity.created(uri).body(notebookService.addNewNoteToNoteBook(notebookAddNoteDTO.getNoteId(), notebookAddNoteDTO.getNotebookId()));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteNotebook(@RequestParam Long notebookId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
        return ResponseEntity.created(uri).body(notebookService.deleteNotebook(notebookId));
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<Collection<Notebook>> getAllTag(@RequestParam Integer userId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
        return ResponseEntity.created(uri).body(notebookService.findAllNotebookByUser(userId));
    }

    @PutMapping(path = "/update")
    public  ResponseEntity<String> updateTag(@RequestBody NotebookUpdateDTO notebookUpdateDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        Notebook updateNotebook = new Notebook(notebookUpdateDTO.getNotebookName());
        updateNotebook.setNotebookId(notebookUpdateDTO.getNotebookId());
        return ResponseEntity.created(uri).body(notebookService.updateNotebook(updateNotebook));
    }
}
