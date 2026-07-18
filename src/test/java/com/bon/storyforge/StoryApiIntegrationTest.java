package com.bon.storyforge;

import com.bon.storyforge.entity.Scene;
import com.bon.storyforge.entity.Story;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest //boots entire application - mvnw spring-boot:run inside the test
@AutoConfigureMockMvc //gives a MockMvc object - simulates HTTP requests hitting controllers without opening a port
public class StoryApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; //java object to json conversion class

    @Test
    void createStory_returnCreatedStoryWithId() throws Exception {
        Story story = new Story();
        story.setText("A romance fantasy about seven sins");

        mockMvc.perform(post("/api/stories")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(story)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").value("A romance fantasy about seven sins"));
    }

    @Test
    void createScene_linksToParentStory() throws  Exception{
        Story story = new Story();
        story.setText("A romance fantasy about seven sins");
        Scene scene = new Scene();
        scene.setTitle("The Tree of Life");
        scene.setContent("The banshee decides to get the rose form the tree of life");

        String responseBody = mockMvc.perform(post("/api/stories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(story)))
                .andReturn().getResponse().getContentAsString();

        long storyId = objectMapper.readTree(responseBody).get("id").asLong();

        mockMvc.perform(post("/api/stories/{storyId}/scenes", storyId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(scene)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("The Tree of Life"))
                .andExpect(jsonPath("$.content").value("The banshee decides to get the rose form the tree of life"));
    }
}
