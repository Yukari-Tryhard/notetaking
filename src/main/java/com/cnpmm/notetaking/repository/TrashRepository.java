package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Trash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashRepository extends JpaRepository<Trash, Long> {
}
