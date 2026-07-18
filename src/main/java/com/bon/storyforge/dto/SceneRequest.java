package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotBlank;

public record SceneRequest(@NotBlank String title, String content) {
}
