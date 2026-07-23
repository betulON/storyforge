package com.bon.storyforge.service;

import com.bon.storyforge.entity.Choice;
import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.ChoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final SceneService sceneService;

    public ChoiceService(ChoiceRepository choiceRepository, SceneService sceneService) {
        this.choiceRepository = choiceRepository;
        this.sceneService = sceneService;
    }

    public Choice createChoice(Choice choice, Long fromSceneId, Long toSceneId){
        choice.setFromScene(sceneService.getSceneById(fromSceneId));
        choice.setToScene(sceneService.getSceneById(toSceneId));
        return choiceRepository.save(choice);
    }

    public Choice getChoiceById(Long id){
        return choiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Choice not found: " + id));
    }

    public List<Choice> getChoicesByStoryId(Long storyId){
        return choiceRepository.findByFromScene_Story_Id(storyId);
    }

    public void deleteChoice(Long id){
        Choice existingChoice = getChoiceById(id);
        choiceRepository.delete(existingChoice);
    }

    public Choice moveChoice(Long choiceId, Long fromSceneId, Long toSceneId){
        Choice existingChoice = getChoiceById(choiceId);
        existingChoice.setFromScene(sceneService.getSceneById(fromSceneId));
        existingChoice.setToScene(sceneService.getSceneById(toSceneId));
        return choiceRepository.save(existingChoice);
    }

    public Choice updateChoiceProperties(Long id, String text){
        Choice existingChoice = getChoiceById(id);
        existingChoice.setText(text);
        return choiceRepository.save(existingChoice);
    }
}
