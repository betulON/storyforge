package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotBlank;

public record ChoiceRequest(@NotBlank String text,
                            Long toSceneId) {}
