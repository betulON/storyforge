package com.bon.storyforge.dto;

import com.bon.storyforge.entity.Scene;

import java.util.List;

public record SceneResponse(Long id, String title, String content, Long storyId,
                            String imageUrl, Double positionX, Double positionY) {

    public static SceneResponse from(Scene scene){
        return new SceneResponse(scene.getId(),
                scene.getTitle(),
                scene.getContent(),
                scene.getStory().getId(),
                scene.getImageUrl(),
                scene.getPositionX(),
                scene.getPositionY()
        );
    }

    public static List<SceneResponse> fromAll(List<Scene> scenes){
        return scenes
                .stream()
                .map(SceneResponse::from)
                .toList();
    }

}
