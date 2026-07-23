package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotNull;

public record ChoicePropertiesRequest(@NotNull String text) {
}
