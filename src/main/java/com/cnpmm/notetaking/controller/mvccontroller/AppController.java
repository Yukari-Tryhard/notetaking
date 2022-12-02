package com.cnpmm.notetaking.controller.mvccontroller;

import com.cnpmm.notetaking.model.*;
import com.cnpmm.notetaking.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/")
public class AppController {
    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NotebookService notebookService;

    public AppController(UserService userService, NoteService noteService, TaskService taskService, TagService tagService, NotebookService notebookService) {
        this.userService = userService;
        this.noteService = noteService;
        this.taskService = taskService;
        this.tagService = tagService;
        this.notebookService = notebookService;
    }

    @GetMapping("new-note")
    public ModelAndView NewNote(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Tag> tags = tagService.findAllTagByUser(user.getId());
        ModelAndView modelAndView = new ModelAndView("new-note");
        modelAndView.addObject("user",user);
        modelAndView.addObject("tags",tags);
        return modelAndView;
    }

    @GetMapping("new-task")
    public ModelAndView NewTask(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Tag> tags = tagService.findAllTagByUser(user.getId());
        ModelAndView modelAndView = new ModelAndView("new-task");
        modelAndView.addObject("user",user);
        modelAndView.addObject("tags",tags);
        return modelAndView;
    }



    @GetMapping("my-note")
    public ModelAndView MyNote(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="note-id", required = false) Long noteId) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Note> notesWithTag = noteService.findAllNoteWithTagByUser(user.getId());
        Collection<Tag> tags = tagService.findAllTagByUser(user.getId());
        ArrayList<Note> notesWithTagArray = new ArrayList(notesWithTag);
        ModelAndView modelAndView = new ModelAndView("my-note");
        modelAndView.addObject("user",user);
        modelAndView.addObject("tags",tags);
        modelAndView.addObject("notesWithTag",notesWithTagArray);
        if (noteId != null){
            modelAndView.addObject("activeNote",noteService.findById(noteId));
        }
        else{
            modelAndView.addObject("activeNote", notesWithTagArray.get(0));
        }
        return modelAndView;
    }

    @GetMapping("my-task")
    public ModelAndView MyTask(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="task-id", required = false) Long taskId) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Task> tasks = taskService.findAllTaskByUser(user.getId());
        Collection<Tag> tags = tagService.findAllTagByUser(user.getId());
        ArrayList<Task> tasksArray = new ArrayList(tasks);
        ModelAndView modelAndView = new ModelAndView("my-task");
        modelAndView.addObject("user",user);
        modelAndView.addObject("tags",tags);
        modelAndView.addObject("tasks",tasks);
        if (taskId != null){
            modelAndView.addObject("activeTask",taskService.findById(taskId));
        }
        else{
            modelAndView.addObject("activeTask", tasksArray.get(0));
        }
        return modelAndView;
    }

    @GetMapping("my-notebook")
    public ModelAndView MyNotebook(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="notebook-id", required = false) Long notebookId) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Notebook> notebooks = notebookService.findAllNotebookByUser(user.getId());
        Collection<Tag> tags = tagService.findAllTagByUser(user.getId());
        Collection<Note> notes = noteService.findAllNoteRecentlyByUser(user.getId());
        ArrayList<Notebook> notebooksArray = new ArrayList(notebooks);
        ModelAndView modelAndView = new ModelAndView("my-notebook");
        modelAndView.addObject("user",user);
        modelAndView.addObject("tags",tags);
        modelAndView.addObject("notebooks",notebooks);
        modelAndView.addObject("notes", notes);
        if (notebookId != null){
            modelAndView.addObject("activeNotebook",notebookService.findById(notebookId));
        }
        else{
            modelAndView.addObject("activeNotebook", notebooksArray.get(0));
        }
        return modelAndView;
    }

    @GetMapping("setting")
    public String Setting() {
        return "setting";
    }

    @GetMapping("home")
    public ModelAndView Home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("home");
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Note> noteRecently = noteService.findAllNoteByUser(user.getId());
        Collection<Note> notes = noteService.findAllNoteRecentlyByUser(user.getId());
        modelAndView.addObject("user",user);
        Collection<Tag> tags = tagService.findAllTagByUser(user.getId());
        modelAndView.addObject("tags",tags);
        modelAndView.addObject("notes", notes);
        modelAndView.addObject("noteRecently", noteRecently);
        return modelAndView;
    }
}
