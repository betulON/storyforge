package com.bon.storyforge.repository;

import com.bon.storyforge.entity.StoryCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CharacterRepository extends JpaRepository<StoryCharacter, Long> {}
