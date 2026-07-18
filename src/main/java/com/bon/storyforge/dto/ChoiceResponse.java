package com.bon.storyforge.dto;

import com.bon.storyforge.entity.Choice;
import com.bon.storyforge.entity.Scene;

import java.util.Optional;

public record ChoiceResponse(Long id, String text, Long fromSceneId, Long toSceneId) {

    public static ChoiceResponse from(Choice choice){
        return new ChoiceResponse(
                choice.getId(),
                choice.getText(),
                choice.getFromScene().getId(),
                choice.getToScene() != null ? choice.getToScene().getId() : null
        );
    }
}
