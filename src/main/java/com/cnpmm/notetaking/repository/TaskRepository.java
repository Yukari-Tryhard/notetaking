package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(
            value = "SELECT * FROM task t WHERE t.user_id = ?1",
            nativeQuery = true
    )
    public Collection<Task> findAllByUser(Integer userId);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE Task t SET t.title = ?2, t.content = ?3, t.start_date = ?4, t.end_date = ?5 WHERE t.task_id = ?1",
            nativeQuery = true
    )
    void UpdateTask(Long taskId, String title, String content, Timestamp startDate, Timestamp endDate);

    @Query(
            value = "Select * from task t WHERE t.user_id = ?1 Order by t.date_updated desc",
            nativeQuery = true
    )
    Collection<Task> findAllTaskRecentlyByUser(Integer userId);
}
