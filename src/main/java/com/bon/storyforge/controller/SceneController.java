package com.bon.storyforge.controller;

import com.bon.storyforge.dto.ScenePositionRequest;
import com.bon.storyforge.dto.SceneRequest;
import com.bon.storyforge.dto.SceneResponse;
import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.service.SceneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/api/scenes/{id}")
    public SceneResponse updateSceneById(@PathVariable Long id, @Valid @RequestBody SceneRequest request){
        Scene updatedScene = new Scene();
        updatedScene.setContent(request.content());
        updatedScene.setImageUrl(request.imageUrl());
        updatedScene.setTitle(request.title());
        return SceneResponse.from(sceneService.updateScene(id, updatedScene));
    }

    @PatchMapping("/api/scenes/{id}/position")
    public SceneResponse updateScenePositionById(@PathVariable Long id,
                                                 @Valid @RequestBody ScenePositionRequest request){
        return SceneResponse.from(sceneService.updateScenePosition(id,
                request.positionX(),
                request.positionY()));
    }

    @DeleteMapping("/api/scenes/{id}")
    public ResponseEntity<Void> deleteScene(@PathVariable Long id){
        sceneService.deleteScene(id);
        return ResponseEntity.noContent().build();
    }
}
