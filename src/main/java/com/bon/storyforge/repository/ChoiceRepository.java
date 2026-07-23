package com.bon.storyforge.repository;

import com.bon.storyforge.entity.Choice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    List<Choice> findByFromScene_Story_Id(Long storyId);

    @Modifying
    @Query("""
        DELETE FROM Choice c
        WHERE c.fromScene.id = :sceneId
            OR c.toScene.id = :sceneId
    """)
    void deleteConnectedToScene(@Param("sceneId") Long sceneId);
}
