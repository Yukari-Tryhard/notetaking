package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.NoteRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import com.cnpmm.notetaking.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public ServiceResponse addNewNote(Note note, String userId) {
        try{
            User user = userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new EntityNotFoundException("User Id: "+ userId + "not found"));
            if (user != null){
                note.setUser(user);
                noteRepository.save(note);
                return new ServiceResponse(200, "add note succesfully");
            }
            return new ServiceResponse(409,"user with id " + userId + " not found");
        }
        catch (Exception ex)
        {
            return new ServiceResponse(500,ex.getMessage());
        }

    }

    public ServiceResponse deleteNote(Long noteId){
        try{
            Note note = noteRepository.findById(noteId).orElse(null);
            if (note != null){
                noteRepository.delete(note);
                noteRepository.RemoveNoteRelationship(noteId);
                return new ServiceResponse(200,"delete note with id " + note.getNoteId() + "success");
            }
            return new ServiceResponse(409, "note with id " + noteId + " not found");
        }
        catch (Exception ex)
        {
            return new ServiceResponse(500,ex.getMessage());
        }
    }
    public Collection<Note> findAllNoteByUser(Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return noteRepository.findAllByUserId(userId);
        }
        return null;
    }

    public ServiceResponse updateNote(Note note){
        try{
            Note existNote = noteRepository.findById(note.getNoteId()).orElse(null);
            if (existNote != null){
                noteRepository.UpdateNote(note.getNoteId(), note.getTitle(), note.getContent());
                return new ServiceResponse(200,"updated note has id " + note.getNoteId());
            }
            return new ServiceResponse(409,"note has id " + note.getNoteId() + " is not exist");
        }catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public Collection<Note> findAllNoteRecentlyByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return noteRepository.findAllNoteRecentlyByUser(userId);
        }
        return null;
    }

    public Collection<Note> findAllNoteWithTagByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return noteRepository.findAllNoteWithTagByUser(userId);
        }
        return null;
    }

    public Note findById(Long noteId) {
        return noteRepository.findById(noteId).orElse(null);
    }
}
