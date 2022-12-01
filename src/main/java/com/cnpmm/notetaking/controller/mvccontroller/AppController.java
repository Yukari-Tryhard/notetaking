package com.cnpmm.notetaking.controller.mvccontroller;

import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Task;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.service.NoteService;
import com.cnpmm.notetaking.service.TaskService;
import com.cnpmm.notetaking.service.UserService;
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

    public AppController(UserService userService, NoteService noteService, TaskService taskService) {
        this.userService = userService;
        this.noteService = noteService;
        this.taskService = taskService;
    }

    @GetMapping("new-note")
    public String Dashboard() {

        return "new-note";
    }
    @GetMapping("my-note")
    public ModelAndView MyNote(HttpServletRequest request, HttpServletResponse response, @RequestParam(name="note-id", required = false) Long noteId) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByEmail(principal.getName());
        Collection<Note> notesWithTag = noteService.findAllNoteWithTagByUser(user.getId());
        ArrayList<Note> notesWithTagArray = new ArrayList(notesWithTag);
        ModelAndView modelAndView = new ModelAndView("my-note");
        modelAndView.addObject("user",user);
        modelAndView.addObject("notesWithTag",notesWithTagArray);
        if (noteId != null){
            modelAndView.addObject("activeNote",noteService.findById(noteId));
        }
        else{
            modelAndView.addObject("activeNote", notesWithTagArray.get(0));
        }
        return modelAndView;
    }

    @GetMapping("my-notebook")
    public String MyNotebook() {
        return "my-notebook";
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
        Collection<Task> taskRecently = taskService.findAllTaskRecentlyByUser(user.getId());
        Collection<Task> tasks = taskService.findAllTaskByUser(user.getId());
        modelAndView.addObject("user",user);
        modelAndView.addObject("notes", notes);
        modelAndView.addObject("noteRecently", noteRecently);
        return modelAndView;
    }
}
