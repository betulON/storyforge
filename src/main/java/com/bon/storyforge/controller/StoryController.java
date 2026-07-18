package com.bon.storyforge.controller;

import com.bon.storyforge.dto.StoryRequest;
import com.bon.storyforge.dto.StoryResponse;
import com.bon.storyforge.entity.Story;
import com.bon.storyforge.service.StoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }

    @GetMapping
    public List<StoryResponse> getAllStories(){
        List<Story> stories = storyService.getAllStories();
        return StoryResponse.fromAll(stories);
    }

    @GetMapping("/{id}")
    public StoryResponse getStory(@PathVariable Long id){
        return StoryResponse.from(storyService.getStoryById(id));
    }

    @PostMapping
    public StoryResponse createStory(@Valid @RequestBody StoryRequest storyRequest){
        Story story = new Story();
        story.setText(storyRequest.text());
        Story savedStory = storyService.createStory(story);
        return StoryResponse.from(savedStory);
    }


}
