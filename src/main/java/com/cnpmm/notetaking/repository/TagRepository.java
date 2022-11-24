package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
