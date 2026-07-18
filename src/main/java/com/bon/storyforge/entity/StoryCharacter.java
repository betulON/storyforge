package com.bon.storyforge.entity;

import jakarta.persistence.*;

@Entity
public class StoryCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CharacterRole characterRole;

    @Enumerated(EnumType.STRING)
    private CharacterSex characterSex;

    private String characterName;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    public StoryCharacter(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CharacterRole getCharacterRole() {
        return characterRole;
    }

    public void setCharacterRole(CharacterRole characterRole) {
        this.characterRole = characterRole;
    }

    public CharacterSex getCharacterSex() {
        return characterSex;
    }

    public void setCharacterSex(CharacterSex characterSex) {
        this.characterSex = characterSex;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
