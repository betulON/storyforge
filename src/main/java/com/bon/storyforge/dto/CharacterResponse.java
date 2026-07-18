package com.bon.storyforge.dto;

import com.bon.storyforge.entity.CharacterRole;
import com.bon.storyforge.entity.CharacterSex;
import com.bon.storyforge.entity.StoryCharacter;

public record CharacterResponse(Long id, String name, CharacterRole role, CharacterSex sex) {

    public static CharacterResponse from(StoryCharacter storyCharacter){
        return new CharacterResponse(
                storyCharacter.getId(),
                storyCharacter.getCharacterName(),
                storyCharacter.getCharacterRole(),
                storyCharacter.getCharacterSex()
        );

    }
}
