package com.bon.storyforge.controller;

import com.bon.storyforge.dto.SceneRequest;
import com.bon.storyforge.dto.SceneResponse;
import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.service.SceneService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SceneController {

    private final SceneService sceneService;

    public SceneController(SceneService sceneService){
        this.sceneService = sceneService;
    }

    @PostMapping("/api/stories/{storyId}/scenes")
    public SceneResponse createScene(@PathVariable Long storyId, @Valid @RequestBody SceneRequest sceneRequest){
        Scene scene = new Scene();
        scene.setTitle(sceneRequest.title());
        scene.setContent(sceneRequest.content());
        scene.setImageUrl(sceneRequest.imageUrl());
        Scene savedScene = sceneService.createScene(storyId, scene);
        return SceneResponse.from(savedScene);
    }

    @GetMapping("/api/stories/{storyId}/scenes")
    public List<SceneResponse> getScenesByStoryId(@PathVariable Long storyId){
        return SceneResponse.fromAll(sceneService.getScenesByStoryId(storyId));
    }

    @PutMapping("/api/scenes/{sceneId}")
    public SceneResponse updateSceneById(@PathVariable Long sceneId, @Valid @RequestBody SceneRequest sceneRequest){
        Scene updatedScene = new Scene();
        updatedScene.setContent(sceneRequest.content());
        updatedScene.setImageUrl(sceneRequest.imageUrl());
        updatedScene.setTitle(sceneRequest.title());
        return SceneResponse.from(sceneService.updateScene(sceneId, updatedScene));
    }
}
