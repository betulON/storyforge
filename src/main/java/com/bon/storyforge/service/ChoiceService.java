package com.bon.storyforge.service;

import com.bon.storyforge.entity.Choice;
import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.ChoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final SceneService sceneService;

    public ChoiceService(ChoiceRepository choiceRepository, SceneService sceneService) {
        this.choiceRepository = choiceRepository;
        this.sceneService = sceneService;
    }

    public Choice createChoice(Choice choice, Long fromSceneId, Long toSceneId){
        Scene fromScene = sceneService.getSceneById(fromSceneId);
        if (toSceneId != null){
            Scene toScene = sceneService.getSceneById(toSceneId);
            choice.setToScene(toScene);
        }
        choice.setFromScene(fromScene);
        return choiceRepository.save(choice);
    }

    public Choice getChoiceById(Long id){
        return choiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Choice not found: " + id));
    }
}
