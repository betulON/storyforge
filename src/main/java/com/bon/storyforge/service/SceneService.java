package com.bon.storyforge.service;

import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.entity.Story;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.ChoiceRepository;
import com.bon.storyforge.repository.SceneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneService {

    private final StoryService storyService;
    private final SceneRepository sceneRepository;
    private final ChoiceRepository choiceRepository;

    public SceneService(StoryService storyService, SceneRepository sceneRepository, ChoiceRepository choiceRepository){
        this.storyService = storyService;
        this.sceneRepository = sceneRepository;
        this.choiceRepository = choiceRepository;
    }

    public Scene createScene(Long storyId, Scene scene){
        Story story = storyService.getStoryById(storyId);
        scene.setStory(story);
        return sceneRepository.save(scene);
    }

    public Scene getSceneById(Long id){
        return sceneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Scene not found: " + id));
    }

    public List<Scene> getScenesByStoryId(Long storyId){
        return sceneRepository.findByStoryId(storyId);
    }

    public Scene updateScene(Long id, Scene updatedScene){
        Scene scene = getSceneById(id);
        scene.setContent(updatedScene.getContent());
        scene.setImageUrl(updatedScene.getImageUrl());
        scene.setTitle(updatedScene.getTitle());
        return sceneRepository.save(scene);
    }

    public Scene updateScenePosition(Long id, Double positionX, Double positionY){
        Scene scene = getSceneById(id);
        scene.setPositionX(positionX);
        scene.setPositionY(positionY);
        return sceneRepository.save(scene);
    }

    @Transactional
    public void deleteScene(Long id){
        Scene scene = getSceneById(id);
        choiceRepository.deleteConnectedToScene(id);
        sceneRepository.delete(scene);
    }

}
