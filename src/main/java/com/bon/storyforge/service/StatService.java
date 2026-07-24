package com.bon.storyforge.service;

import com.bon.storyforge.entity.Stat;
import com.bon.storyforge.entity.Story;
import com.bon.storyforge.entity.StoryCharacter;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.CharacterRepository;
import com.bon.storyforge.repository.StatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService {

    private final StatRepository statRepository;
    private final StoryService storyService;
    private final CharacterRepository characterRepository;

    public StatService(StatRepository statRepository, StoryService storyService, CharacterRepository characterRepository) {
        this.statRepository = statRepository;
        this.storyService = storyService;
        this.characterRepository = characterRepository;
    }

    public Stat createStat(Long storyId, String name, int value, Long characterId){
        Stat stat = new Stat();
        stat.setStory(storyService.getStoryById(storyId));
        stat.setName(name);
        stat.setValue(value);
        stat.setCharacter(resolveCharacter(characterId));
        return statRepository.save(stat);
    }

    public Stat updateStat(Long id, String name, int value, Long characterId){
        Stat stat = new Stat();
        stat.setName(name);
        stat.setValue(value);
        stat.setCharacter(resolveCharacter(characterId));
        return statRepository.save(stat);
    }

    public void deleteStat(Long id){
        statRepository.deleteById(id);
    }

    public List<Stat> getStatsByStoryId(Long storyId){
        return statRepository.findByStoryId(storyId);
    }

    public Stat getStatById(Long id){
        return statRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stat not found: " + id));
    }

    private StoryCharacter resolveCharacter(Long characterId){
        if (characterId == null) return null;
        return characterRepository.findById(characterId).orElseThrow(
                () -> new ResourceNotFoundException("Character not found: " + characterId));
    }

}
