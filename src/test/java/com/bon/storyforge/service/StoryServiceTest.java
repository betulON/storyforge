package com.bon.storyforge.service;

import com.bon.storyforge.entity.Story;
import com.bon.storyforge.repository.StoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {

    @Mock
    private StoryRepository storyRepository;

    @InjectMocks
    private StoryService storyService;

    @Test
    void getStoryById_returnStory_whenFound(){
        Story story = new Story();
        story.setId(1L);
        story.setText("A cyberpunk mystery");

        when(storyRepository.findById(1L)).thenReturn(Optional.of(story));
        Story result = storyService.getStoryById(1L);
        assertThat(result.getText()).isEqualTo("A cyberpunk mystery");
    }

    @Test
    void getStoryById_throws_whenNotFound(){
        when(storyRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> storyService.getStoryById(1L)).isInstanceOf(RuntimeException.class);
    }
}
