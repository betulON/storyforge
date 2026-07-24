package com.bon.storyforge.controller;

import com.bon.storyforge.dto.StatRequest;
import com.bon.storyforge.dto.StatResponse;
import com.bon.storyforge.service.StatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/api/stats/{id}")
    public StatResponse getStatById(@PathVariable Long id){
        return StatResponse.from(statService.getStatById(id));
    }

    @GetMapping("/api/stories/{storyId}/stats")
    public List<StatResponse> getStats(@PathVariable Long storyId){
        return StatResponse.fromAll(statService.getStatsByStoryId(storyId));
    }

    @PostMapping("/api/stories/{storyId}/stats")
    public StatResponse createStat(@PathVariable Long storyId, @Valid @RequestBody StatRequest request){
        return StatResponse.from(statService.createStat(storyId, request.name(),request.value(), request.characterId()));
    }

    @PutMapping("/api/stats/{id}")
    public StatResponse updateStat(@PathVariable Long id, @Valid @RequestBody StatRequest request){
        return StatResponse.from(statService.updateStat(id, request.name(), request.value(), request.characterId()));
    }

    @DeleteMapping("/api/stats/{id}")
    public ResponseEntity<Void> deleteStat(@PathVariable Long id){
        statService.deleteStat(id);
        return ResponseEntity.noContent().build();
    }
}
