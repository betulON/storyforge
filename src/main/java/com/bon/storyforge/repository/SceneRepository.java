package com.bon.storyforge.repository;

import com.bon.storyforge.entity.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SceneRepository extends JpaRepository<Scene, Long> {

    List<Scene> findByStoryId(Long storyId);

}
