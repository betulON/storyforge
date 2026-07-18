package com.bon.storyforge.controller;

import com.bon.storyforge.dto.SceneRequest;
import com.bon.storyforge.dto.SceneResponse;
import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.service.SceneService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        Scene savedScene = sceneService.createScene(storyId, scene);
        return SceneResponse.from(savedScene);
    }
}
