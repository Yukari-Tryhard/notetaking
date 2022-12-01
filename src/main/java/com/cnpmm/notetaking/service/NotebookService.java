package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.NoteRepository;
import com.cnpmm.notetaking.repository.NotebookRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import com.cnpmm.notetaking.util.ServiceResponse;
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
    public ServiceResponse addNewNoteBook(Notebook notebook, Integer userId){
        try{
            User user = userRepository.findById(userId).orElse(null);
            if (user != null){
                notebook.setUser(user);
                notebookRepository.save(notebook);
                return new ServiceResponse(200,"save notebook successfully");
            }
            return new ServiceResponse(409,"user with id "+userId + " not found");
        }
        catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public ServiceResponse addNewNoteToNoteBook(Long noteId, Long notebookId){
        try{
            Note note = noteRepository.findById(notebookId).orElse(null);
            if (note != null){
                Notebook notebook = notebookRepository.findById(notebookId).orElse(null);
                if (notebook != null){
                    if (!notebook.getNotes().contains(note) ){
                        notebookRepository.AddNoteToNotebook(noteId, notebookId);
                        return new ServiceResponse(200,"save notebook successfully");
                    }
                    return new ServiceResponse(200,"note already exits");
                }
                return new ServiceResponse(409,"notebook with id "+notebookId +  " is not exits");
            }
            return new ServiceResponse(409,"note with id "+noteId +  " is not exits");
        }
        catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public ServiceResponse deleteNotebook(Long notebookId){
        try{
            Notebook notebook = notebookRepository.findById(notebookId).orElse(null);
            if (notebook != null){
                notebookRepository.delete(notebook);
                notebookRepository.RemoveNotebookRelationship(notebookId);
                return new ServiceResponse(200,"delete notebook with id " + notebook.getNotebookId() + "successfully");
            }
            return new ServiceResponse(409,"notebook with id " + notebookId + " not found");
        }
        catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public Collection<Notebook> findAllNotebookByUser(Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return notebookRepository.findAllByUser(userId);
        }
        return null;
    }

    public ServiceResponse updateNotebook(Notebook notebook){
        try{
            Notebook existNotebook = notebookRepository.findById(notebook.getNotebookId()).orElse(null);
            if (existNotebook != null){
                notebookRepository.UpdateNotebook(notebook.getNotebookId(), notebook.getNotebookName());
                return new ServiceResponse(200,"updated notebook has id " + notebook.getNotebookId());
            }
             return new ServiceResponse(200,"notebook has id " + notebook.getNotebookId() + " is not exist");
        }catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }
}
