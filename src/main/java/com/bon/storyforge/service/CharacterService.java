package com.bon.storyforge.service;

import com.bon.storyforge.entity.Story;
import com.bon.storyforge.entity.StoryCharacter;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final StoryService storyService;

    public CharacterService(CharacterRepository characterRepository, StoryService storyService) {
        this.characterRepository = characterRepository;
        this.storyService = storyService;
    }

    public StoryCharacter createCharacter(StoryCharacter storyCharacter, Long storyId){
        Story story = storyService.getStoryById(storyId);
        storyCharacter.setStory(story);
        return characterRepository.save(storyCharacter);
    }

    public StoryCharacter getCharacterById(Long id){
        return characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character not found " + id));
    }
}
