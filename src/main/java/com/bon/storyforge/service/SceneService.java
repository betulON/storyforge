package com.bon.storyforge.service;

import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.entity.Story;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.SceneRepository;
import org.springframework.stereotype.Service;

@Service
public class SceneService {

    private final StoryService storyService;
    private final SceneRepository sceneRepository;

    public SceneService(StoryService storyService, SceneRepository sceneRepository){
        this.storyService = storyService;
        this.sceneRepository = sceneRepository;
    }

    public Scene createScene(Long storyId, Scene scene){
        Story story = storyService.getStoryById(storyId);
        scene.setStory(story);
        return sceneRepository.save(scene);
    }

    public Scene getSceneById(Long id){
        return sceneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Scene not found: " + id));
    }

}
