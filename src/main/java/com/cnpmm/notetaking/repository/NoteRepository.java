package com.cnpmm.notetaking.repository;


import com.cnpmm.notetaking.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public interface NoteRepository extends  JpaRepository<Note, Long>{

    @Query(
            value = "SELECT * FROM note n WHERE n.user_id = ?1",
            nativeQuery = true
    )
    Collection<Note> findAllByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from notebook_notes nn where nn.notes_note_id = ?1",
            nativeQuery = true
    )
    public void RemoveNoteRelationship(Long noteId);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(
            value = "UPDATE Note n SET n.title = ?2, n.content = ?3 WHERE n.note_id = ?1",
            nativeQuery = true
    )
    public void UpdateNote(Long noteId, String title, String content);

    @Query(
            value = "Select * from note n WHERE n.user_id = ?1 Order by n.date_updated desc",
            nativeQuery = true
    )
    Collection<Note> findAllNoteRecentlyByUser(Integer userId);

    @Query(
            value = "Select * from note n WHERE n.user_id = ?1 Order by n.date_updated desc",
            nativeQuery = true
    )
    Collection<Note> findAllNoteWithTagByUser(Integer userId);

    @Query(
            value = "UPDATE tag t SET t.note_note_id = null WHERE t.note_note_id = ?1",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void clearTag(Long noteId);
}
