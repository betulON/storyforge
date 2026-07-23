package com.bon.storyforge.controller;

import com.bon.storyforge.dto.ChoiceMoveRequest;
import com.bon.storyforge.dto.ChoiceRequest;
import com.bon.storyforge.dto.ChoiceResponse;
import com.bon.storyforge.dto.ChoicePropertiesRequest;
import com.bon.storyforge.entity.Choice;
import com.bon.storyforge.service.ChoiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChoiceController {

    private final ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping("/api/choices/{id}")
    public ChoiceResponse getChoice(@PathVariable Long id){
        Choice choice = choiceService.getChoiceById(id);
        return ChoiceResponse.from(choice);
    }

    @PostMapping("/api/scenes/{fromSceneId}/choices")
    public ChoiceResponse createChoice(@PathVariable Long fromSceneId, @Valid @RequestBody ChoiceRequest request){
        Choice choice = new Choice();
        choice.setText(request.text());
        Choice savedChoice = choiceService.createChoice(choice, fromSceneId, request.toSceneId());
        return ChoiceResponse.from(savedChoice);
    }

    @GetMapping("/api/stories/{storyId}/choices")
    public List<ChoiceResponse> getChoicesByStoryId(@PathVariable Long storyId){
        return ChoiceResponse.fromAll(choiceService.getChoicesByStoryId(storyId));
    }

    @PutMapping("/api/choices/{id}")
    public ChoiceResponse updateChoiceProperties(@PathVariable Long id, @Valid @RequestBody ChoicePropertiesRequest request){
        return ChoiceResponse.from(choiceService.updateChoiceProperties(id, request.text()));
    }

    @PatchMapping("/api/choices/{id}/connection")
    public ChoiceResponse moveChoice(@PathVariable Long id, @Valid @RequestBody ChoiceMoveRequest request){
        return ChoiceResponse.from(choiceService.moveChoice(id, request.fromSceneId(), request.toSceneId()));
    }

    @DeleteMapping("/api/choices/{id}")
    public ResponseEntity<Void> deleteChoiceById(@PathVariable Long id){
        choiceService.deleteChoice(id);
        return ResponseEntity.noContent().build();
    }
}
