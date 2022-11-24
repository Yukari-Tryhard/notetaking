package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
