package com.bon.storyforge.controller;

import com.bon.storyforge.dto.CharacterRequest;
import com.bon.storyforge.dto.CharacterResponse;
import com.bon.storyforge.entity.CharacterRole;
import com.bon.storyforge.entity.CharacterSex;
import com.bon.storyforge.entity.StoryCharacter;
import com.bon.storyforge.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/api/stories/{storyId}/characters")
    public CharacterResponse createCharacter(@PathVariable Long storyId,
                                             @Valid @RequestBody CharacterRequest characterRequest){
        StoryCharacter storyCharacter = new StoryCharacter();
        storyCharacter.setCharacterName(characterRequest.characterName());
        storyCharacter.setCharacterRole(characterRequest.characterRole());
        storyCharacter.setCharacterSex(characterRequest.characterSex());
        StoryCharacter savedCharacter = characterService.createCharacter(storyCharacter, storyId);
        return CharacterResponse.from(savedCharacter);

    }

    @GetMapping("/api/characters/{id}")
    public CharacterResponse getCharacter(Long id){
        StoryCharacter storyCharacter =  characterService.getCharacterById(id);
        return CharacterResponse.from(storyCharacter);
    }


}
