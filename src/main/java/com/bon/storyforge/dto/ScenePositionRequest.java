package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotNull;

public record ScenePositionRequest(@NotNull Double positionX, @NotNull Double positionY) {
}
