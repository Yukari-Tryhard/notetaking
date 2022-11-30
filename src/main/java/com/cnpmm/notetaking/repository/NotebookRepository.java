package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Note;
import com.cnpmm.notetaking.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE notebook_roles nr SET nr.notebook_name = ?1.notebook_name",
            nativeQuery = true
    )
    public void UpdateNotebook(Notebook notebook);

    @Modifying
    @Transactional
    @Query(
            value = "insert  into notebook_notes(notebook_notebook_id, notes_note_id) value (?2, ?1)",
            nativeQuery = true
    )
    public void AddNoteToNotebook(Long noteId, Long notebookId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from notebook_notes nn where nn.notebook_notebook_id = ?1",
            nativeQuery = true
    )
    public void RemoveNotebookRelationship(Long notebookId);

    @Query(
            value=" SELECT * FROM NOTEBOOK w WHERE w.user_id = ?1",
            nativeQuery = true
    )
    public Collection<Notebook> findAllByUser(Integer userId);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query(
            value = "UPDATE Notebook n SET n.notebook_name = ?2 WHERE n.notebook_id = ?1",
            nativeQuery = true
    )
    public void UpdateNotebook(Long notebookId, String notebookName);
}
