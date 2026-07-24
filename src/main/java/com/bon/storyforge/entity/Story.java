package com.bon.storyforge.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    // mappedBy = "story" : I'm not the one that owns the FK, story field in Stat owns it
    // cascade = CascadeType.ALL : flow is from parent to down, story -> stat
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stat> stats = new ArrayList<>();

    public Story(){}


    public List<Stat> getStats() {
        return stats;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

}