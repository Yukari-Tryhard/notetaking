package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {
}
