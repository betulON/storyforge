package com.bon.storyforge.dto;

import jakarta.validation.constraints.NotBlank;

public record StoryRequest(@NotBlank String text) {}
