package com.bon.storyforge.dto;

import com.bon.storyforge.entity.Story;

import java.util.List;

public record StoryResponse(Long id, String text) {

    public static StoryResponse from(Story story){ //convert an entity into its dto
        return new StoryResponse(story.getId(), story.getText());
    }

    public static List<StoryResponse> fromAll(List<Story> stories){
        return stories
                .stream()
                .map(StoryResponse::from)
                .toList();
    }
}
