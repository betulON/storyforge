package com.bon.storyforge.dto;

import com.bon.storyforge.entity.Scene;

public record SceneResponse(Long id, String title, String content, Long storyId) {

    public static SceneResponse from(Scene scene){
        return new SceneResponse(scene.getId(), scene.getTitle(), scene.getContent(), scene.getStory().getId());
    }

}
