package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.NoteRepository;
import com.cnpmm.notetaking.repository.NotebookRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NotebookService {

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    public NotebookService(NotebookRepository notebookRepository, UserRepository userRepository, NoteRepository noteRepository)
    {
        this.notebookRepository = notebookRepository;
        this.userRepository = userRepository;
        this.notebookRepository = notebookRepository;
    }
    public Notebook addNewNoteBook(Notebook notebook, Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            notebook.setUser(user);
            return notebookRepository.save(notebook);
        }
        return null;
    }

    public Notebook addNewNoteToNoteBook(Long noteId, Long notebookId){
        Note note = noteRepository.findById(notebookId).orElse(null);
        if (note != null){
            Notebook notebook = notebookRepository.findById(notebookId).orElse(null);
            if (notebook != null){
                if (!notebook.getNotes().contains(note) ){
                    notebookRepository.AddNoteToNotebook(noteId, notebookId);
                    return notebook;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public String deleteNotebook(Long notebookId){
        Notebook notebook = notebookRepository.findById(notebookId).orElse(null);
        if (notebook != null){
            notebookRepository.delete(notebook);
            notebookRepository.RemoveNotebookRelationship(notebookId);
            return "delete notebook with id " + notebook.getNotebookId() + "success";
        }
        return "notebook with id " + notebookId + " not found";
    }

    public Collection<Notebook> findAllNotebookByUser(Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return notebookRepository.findAllByUser(userId);
        }
        return null;
    }

    public String updateNotebook(Notebook notebook){
        try{
            Notebook existNotebook = notebookRepository.findById(notebook.getNotebookId()).orElse(null);
            if (existNotebook != null){
                notebookRepository.UpdateNotebook(notebook.getNotebookId(), notebook.getNotebookName());
                return "updated notebook has id " + notebook.getNotebookId();
            }
            return "notebook has id " + notebook.getNotebookId() + " is not exist";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }
}
