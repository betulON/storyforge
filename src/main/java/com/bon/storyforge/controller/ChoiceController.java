package com.bon.storyforge.controller;

import com.bon.storyforge.dto.ChoiceRequest;
import com.bon.storyforge.dto.ChoiceResponse;
import com.bon.storyforge.entity.Choice;
import com.bon.storyforge.service.ChoiceService;
import jakarta.validation.Valid;
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
    public ChoiceResponse createChoice(@PathVariable Long fromSceneId, @Valid @RequestBody ChoiceRequest choiceRequest){
        Choice choice = new Choice();
        choice.setText(choiceRequest.text());
        Choice savedChoice = choiceService.createChoice(choice, fromSceneId, choiceRequest.toSceneId());
        return ChoiceResponse.from(savedChoice);
    }

    @GetMapping("/api/stories/{storyId}/choices")
    public List<ChoiceResponse> getChoicesByStoryId(@PathVariable Long storyId){
        return ChoiceResponse.fromAll(choiceService.getChoicesByStoryId(storyId));
    }
}
