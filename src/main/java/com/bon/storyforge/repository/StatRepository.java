package com.bon.storyforge.repository;

import com.bon.storyforge.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatRepository extends JpaRepository<Stat, Long> {

    List<Stat> findByStoryId(Long storyId);
}
