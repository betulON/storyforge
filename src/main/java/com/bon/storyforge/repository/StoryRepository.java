package com.bon.storyforge.repository;

import com.bon.storyforge.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {}
