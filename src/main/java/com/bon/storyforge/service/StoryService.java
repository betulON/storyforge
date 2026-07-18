package com.bon.storyforge.service;

import com.bon.storyforge.entity.Story;
import com.bon.storyforge.exception.ResourceNotFoundException;
import com.bon.storyforge.repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }

    public Story createStory(Story story){
        return storyRepository.save(story);
    }

    public List<Story> getAllStories(){
        return storyRepository.findAll();
    }

    public Story getStoryById(Long id){
        return storyRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Story not found: " + id));
    }
}
