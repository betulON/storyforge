package com.bon.storyforge.dto;

import com.bon.storyforge.entity.Choice;

import java.util.List;

public record ChoiceResponse(Long id, String text, Long fromSceneId, Long toSceneId) {

    public static ChoiceResponse from(Choice choice){
        return new ChoiceResponse(
                choice.getId(),
                choice.getText(),
                choice.getFromScene().getId(),
                choice.getToScene().getId()
        );
    }

    public static List<ChoiceResponse> fromAll(List<Choice> choices){
        return choices.stream().map(ChoiceResponse::from).toList();
    }
}
