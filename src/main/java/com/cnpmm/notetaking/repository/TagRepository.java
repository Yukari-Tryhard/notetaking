package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT t FROM Tag t WHERE t.tagName = ?1")
    User findByTagName(String tagName);

    @Query(
            value = "SELECT * FROM tag t WHERE t.tag_name = ?1 AND t.user_id = ?2",
            nativeQuery = true
    )
    Tag findTagByTagNameWithUserId(String tagName, Integer userId);

    @Query(
            value = "SELECT * FROM tag t WHERE t.user_id = ?1",
            nativeQuery = true
    )
    Collection<Tag> findAllTagByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE Tag t SET t.tag_name = ?2 WHERE t.tag_id = ?1",
            nativeQuery = true
    )
    void UpdateTag(Long tagId, String tagName);
}
