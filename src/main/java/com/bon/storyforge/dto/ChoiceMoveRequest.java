package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotNull;

public record ChoiceMoveRequest(@NotNull Long fromSceneId, @NotNull Long toSceneId) {
}
