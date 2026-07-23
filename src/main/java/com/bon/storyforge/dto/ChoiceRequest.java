package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChoiceRequest(@NotBlank String text,
                            @NotNull Long toSceneId) {}
