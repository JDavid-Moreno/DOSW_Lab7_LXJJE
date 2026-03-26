package edu.eci.dosw.TechCup.model;


import edu.eci.dosw.TechCup.entity.FileEntity;

import java.util.List;

public class Team {
    private Long id;
    private String name;
    private List<String> colors;
    private FileEntity shield;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public FileEntity getShield() {
        return shield;
    }

    public void setShield(FileEntity shield) {
        this.shield = shield;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

}
