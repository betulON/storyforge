package com.bon.storyforge.dto;

import com.bon.storyforge.entity.CharacterRole;
import com.bon.storyforge.entity.CharacterSex;
import jakarta.validation.constraints.NotBlank;

public record CharacterRequest(@NotBlank String characterName,
                               CharacterRole characterRole,
                               CharacterSex characterSex) {}
