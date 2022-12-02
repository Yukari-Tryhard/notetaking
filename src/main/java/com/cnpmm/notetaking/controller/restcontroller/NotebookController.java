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
import com.cnpmm.notetaking.util.GenericResponse;
import com.cnpmm.notetaking.util.ServiceResponse;
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
    public ResponseEntity<GenericResponse<Notebook>> registerNewNotebook(@RequestBody NotebookAddDTO notebookAddDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            Notebook newNotebook = new Notebook(notebookAddDTO.getNotebookName());
            ServiceResponse notebookServiceResponse = notebookService.addNewNoteBook(newNotebook, notebookAddDTO.getUserId());
            return ResponseEntity.created(uri).body(new GenericResponse<Notebook>(notebookServiceResponse,newNotebook));
        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @PostMapping(path = "/add-note")
    public ResponseEntity<GenericResponse<?>> registerNewNoteToNotebook(@RequestBody NotebookAddNoteDTO notebookAddNoteDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add-note").toUriString());
            ServiceResponse notebookServiceResponse =  notebookService.addNewNoteToNoteBook(notebookAddNoteDTO.getNoteId(), notebookAddNoteDTO.getNotebookId());
            return ResponseEntity.created(uri).body(new GenericResponse<Notebook>(notebookServiceResponse,null));
        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add-note").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<GenericResponse<?>> deleteNotebook(@RequestParam Long notebookId){
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
            ServiceResponse notebookServiceResponse = notebookService.deleteNotebook(notebookId);
            return ResponseEntity.created(uri).body(new GenericResponse<Notebook>(notebookServiceResponse, null));
        }
        catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delete").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<GenericResponse<Collection<Notebook>>> getAllNotebook(@RequestParam Integer userId){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<Collection<Notebook>>("get all notebook successfully",200,notebookService.findAllNotebookByUser(userId)));
        }
        catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }

    }

    @PutMapping(path = "/update")
    public  ResponseEntity<GenericResponse<Notebook>> updateNotebook(@RequestBody NotebookUpdateDTO notebookUpdateDTO){
    try{
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        Notebook updateNotebook = new Notebook(notebookUpdateDTO.getNotebookName());
        updateNotebook.setNotebookId(notebookUpdateDTO.getNotebookId());
        ServiceResponse notebookServiceResponse = notebookService.updateNotebook(updateNotebook);
        return ResponseEntity.created(uri).body(new GenericResponse<Notebook>(notebookServiceResponse, updateNotebook));

    }
    catch (Exception ex){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
    }
    }
}
