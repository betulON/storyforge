package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotBlank;

public record StatRequest(@NotBlank String name, int value, Long characterId) {
}
