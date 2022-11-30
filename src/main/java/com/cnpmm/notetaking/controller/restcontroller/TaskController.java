package com.cnpmm.notetaking.controller.restcontroller;

import com.cnpmm.notetaking.dto.note.NoteUpdateDTO;
import com.cnpmm.notetaking.dto.tag.TagAddDTO;
import com.cnpmm.notetaking.dto.task.TaskAddDTO;
import com.cnpmm.notetaking.dto.task.TaskUpdateDTO;
import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.Task;
import com.cnpmm.notetaking.service.TaskService;
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
    public ResponseEntity<Task> addNewTag(@RequestBody TaskAddDTO taskAddDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/add").toUriString());
        Task task = new Task(taskAddDTO.getStartDate(), taskAddDTO.getEndDate(), taskAddDTO.getTitle(), taskAddDTO.getContent());
        return ResponseEntity.created(uri).body(taskService.addTaskByUser(task, taskAddDTO.getUserId()));
    }

    @GetMapping(path = "/get-all")
    public  ResponseEntity<Collection<Task>> getAllTag(@RequestParam Integer userId){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get-all").toUriString());
        return ResponseEntity.created(uri).body(taskService.findAllTaskByUser(userId));
    }

    @PutMapping(path = "/update")
    public  ResponseEntity<String> updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/update").toUriString());
        Task updateTask = new Task(taskUpdateDTO.getStartDate(), taskUpdateDTO.getEndDate(), taskUpdateDTO.getTitle(), taskUpdateDTO.getContent());
        updateTask.setTaskId(taskUpdateDTO.getTaskId());
        return ResponseEntity.created(uri).body(taskService.updateTask(updateTask));
    }
}
