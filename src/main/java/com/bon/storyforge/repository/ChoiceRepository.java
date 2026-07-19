package com.bon.storyforge.repository;

import com.bon.storyforge.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    List<Choice> findByFromScene_Story_Id(Long storyId);

}
