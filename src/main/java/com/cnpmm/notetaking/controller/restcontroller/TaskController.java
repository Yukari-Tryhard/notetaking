package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.note.NoteUpdateDTO;
import com.cnpmm.notetaking.dto.tag.TagAddDTO;
import com.cnpmm.notetaking.dto.task.TaskAddDTO;
import com.cnpmm.notetaking.dto.task.TaskUpdateDTO;
import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.Task;
import com.cnpmm.notetaking.service.TaskService;
import com.cnpmm.notetaking.util.GenericResponse;
import com.cnpmm.notetaking.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController()
@RequestMapping(path = "api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(path = "/add")
    public ResponseEntity<GenericResponse<Task>> addNewTag(@RequestBody TaskAddDTO taskAddDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            Task task = new Task(taskAddDTO.getStartDate(), taskAddDTO.getEndDate(), taskAddDTO.getTitle(), taskAddDTO.getContent(), taskAddDTO.isDone());
            ServiceResponse taskServiceResponse = taskService.addTaskByUser(task, taskAddDTO.getUserId());
            return ResponseEntity.created(uri).body(new GenericResponse<Task>(taskServiceResponse,task));
        }
        catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<Task>(ex.getMessage(), 500,null));
        }
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<GenericResponse<Collection<Task>>> getAllTag(@RequestParam Integer userId){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>("created succesfully", 200,taskService.findAllTaskByUser(userId)));
        }catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<>(ex.getMessage(), 500,null));
        }

    }

    @PutMapping(path = "/update")
    public  ResponseEntity<GenericResponse<Task>> updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO){
        try{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            Task updateTask = new Task(taskUpdateDTO.getStartDate(), taskUpdateDTO.getEndDate(), taskUpdateDTO.getTitle(), taskUpdateDTO.getContent(), taskUpdateDTO.isDone());
            updateTask.setTaskId(taskUpdateDTO.getTaskId());
            ServiceResponse taskServiceResponse =  taskService.updateTask(updateTask);
            return ResponseEntity.created(uri).body(new GenericResponse<Task>(taskServiceResponse,updateTask));
        }
        catch (Exception ex){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
            return ResponseEntity.created(uri).body(new GenericResponse<Task>(ex.getMessage(), 500,null));
        }

    }
}
