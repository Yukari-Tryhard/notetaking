package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.NoteRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
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

    public Collection<Note> findNoteByUser(String userId){
        return noteRepository.findAllByUserId(userId);
    }

    public Note addNewNote(Note note, String userId) {
        User user = userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new EntityNotFoundException("User Id: "+ userId + "not found"));
        if (user != null){
            note.setUser((user));
        }
        return noteRepository.save(note);
    }

}
