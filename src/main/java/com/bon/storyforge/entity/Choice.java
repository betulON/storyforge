package com.bon.storyforge.entity;

import jakarta.persistence.*;

@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "from_scene_id")
    private Scene fromScene;

    @ManyToOne
    @JoinColumn(name= "to_scene_id")
    private Scene toScene;

    public Choice(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Scene getFromScene() {
        return fromScene;
    }

    public void setFromScene(Scene fromScene) {
        this.fromScene = fromScene;
    }

    public Scene getToScene() {
        return toScene;
    }

    public void setToScene(Scene toScene) {
        this.toScene = toScene;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
