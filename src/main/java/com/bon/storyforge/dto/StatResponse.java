package com.bon.storyforge.dto;

import com.bon.storyforge.entity.Stat;

import java.util.List;

public record StatResponse(Long id, String name, int value, Long characterId, Long storyId) {

    public static StatResponse from(Stat stat){
        Long characterId = stat.getCharacter() != null ? stat.getCharacter().getId() : null;
        return new StatResponse(stat.getId(), stat.getName(), stat.getValue(),
                stat.getCharacter().getId(), stat.getStory().getId());
    };

    public static List<StatResponse> fromAll(List<Stat> stats){
        return stats.stream().map(StatResponse::from).toList();
    }
}
