package com.cnpmm.notetaking.repository;


import com.cnpmm.notetaking.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface NoteRepository extends  JpaRepository<Note, Long>{

    @Query("SELECT n FROM Note n JOIN User u ON n.user.id = u.id WHERE n.user.id = ?1")
    Collection<Note> findAllByUserId(String userId);

}
